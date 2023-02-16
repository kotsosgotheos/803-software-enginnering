package persistence.sqlite.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.src.entities.Identifiable;

public class SqliteApi {
    private String tablename;
    private Connection conn;

    private void serialize_object(Object obj, PreparedStatement ps) throws SQLException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(baos);
        oout.writeObject(obj);
        oout.close();
        ps.setBytes(1, baos.toByteArray());
    }

    private Object deserialize_object(ResultSet rs, String column) throws SQLException, IOException, ClassNotFoundException {
        byte serialized_buffer[] = rs.getBytes(column);
        if(serialized_buffer != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized_buffer);
            return new ObjectInputStream(bais).readObject();
        }
        return null;
    }

    private void create_table(int number_of_columns) {
        try {
            Statement st = this.conn.createStatement();
            String create_query = "create table " + this.tablename + "( ";
            for(int i = 0; i < number_of_columns-1; i++)
                create_query += "c" + i + " mediumtext, ";
            create_query += "c" + (number_of_columns-1) + " mediumtext)";
            st.execute(create_query);
        }
        catch(SQLException e) {}
    }

    public SqliteApi(String tablename) {
        this.tablename = tablename;

        try {
            String dblink = "jdbc:sqlite:./persistence/sqlite/src/Database.db";
            this.conn = DriverManager.getConnection(dblink);
        }
        catch(SQLException e) {}
    }

    public void save(Identifiable item) {
        /* NOTE Ensure that we know the number of columns before creating the table */
        int number_of_columns = item.getClass().getDeclaredFields().length;
        this.create_table(number_of_columns);
        /* TODO Tragic SQL injection hazard */
        String insert_statement = "insert into " + this.tablename + " values (";
		for(int i = 0; i < number_of_columns-1; i++)
            insert_statement += "?, ";
		insert_statement += "?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(insert_statement);
            serialize_object(item, ps);
			ps.execute();
        }
        catch(SQLException e) {}
        catch(IOException e) {}
    }

    public Identifiable get_by_id(String id) {
        ArrayList<Identifiable> items = get_all_items();
        for(Identifiable item : items) {
            if(item.id.equals(id))
                return item;
        }
        return null;
    }

    public ArrayList<Identifiable> get_all_items() {
        ArrayList<Identifiable> items = new ArrayList<Identifiable>();

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from " + this.tablename);
            while(rs.next())
                items.add((Identifiable)deserialize_object(rs, "c0"));
            rs.close();
            st.close();
        }
        catch(SQLException e) {}
        catch(ClassNotFoundException e) {}
        catch(IOException e) {}

        return items;
    }

    public void update(String id, Identifiable new_item) {
        ArrayList<Identifiable> items = this.get_all_items();
        for(int i = 0; i < items.size(); i++)
            if(items.get(i).id.equals(id)) {
                items.set(i, new_item);
                break;
            }
        
        try {
            Statement st = this.conn.createStatement();
            st.execute("delete from " + this.tablename);
        }
        catch(Exception e) {}
        
        for(Identifiable item : items)
            this.save(item);
    }

    public void delete(String id) {
        /* TODO Obviously works as long as RAM > dbsize (figure out actual commands) */
        /* TODO Not working on distributed database systems */
        ArrayList<Identifiable> items = this.get_all_items();
        for(int i = 0; i < items.size(); i++)
            if(items.get(i).id.equals(id)) {
                items.remove(i);
                break;
            }
        
        try {
            Statement st = this.conn.createStatement();
            st.execute("delete from " + this.tablename);
        }
        catch(Exception e) {}
        
        for(Identifiable item : items)
            this.save(item);
    }
}
