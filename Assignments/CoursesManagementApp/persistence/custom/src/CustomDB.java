package persistence.custom.src;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import app.src.entities.Identifiable;

public class CustomDB {
    private ArrayList<Identifiable> db = null;
    private String dbname;

    private void read_db_file() {
        this.db = new ArrayList<Identifiable>();
        try {
            FileInputStream fd = new FileInputStream(this.dbname);
            ObjectInputStream in = new ObjectInputStream(fd);
            this.db = (ArrayList<Identifiable>)in.readObject();
            in.close();
            fd.close();
        }
        catch(IOException e) {}
        catch(ClassNotFoundException e) {}
    }

    private void write_db_file() {
        try {
            FileOutputStream fd = new FileOutputStream(this.dbname);
            ObjectOutputStream out = new ObjectOutputStream(fd);
            out.writeObject(this.db);
            out.close();
            fd.close();
        }
        catch(IOException e) {}
    }

    public CustomDB(String dbname) {
        this.db = new ArrayList<Identifiable>();
        this.dbname = "persistence/custom/src/" + dbname + ".db";
        
        try {
            File file = new File(this.dbname);
            if(!file.exists()) {
                FileOutputStream fd = new FileOutputStream(this.dbname);
                fd.close();
            }
        }
        catch(IOException e) {}
    }

    public void save(Identifiable item) {
        this.db.add(item);
        write_db_file();
    }

    public Identifiable get_by_id(String id) {
        read_db_file();

        for(Identifiable item : this.db)
            if(item.id.equals(id))
                return item;
        return null;
    }

    public ArrayList<Identifiable> get_all_items() {
        read_db_file();

        return this.db;
    }

    public void update(String id, Identifiable new_item) {
        read_db_file();

        for(int i = 0; i < this.db.size(); i++)
            if(this.db.get(i).id.equals(id))
                this.db.set(i, new_item);

        write_db_file();
    }

    public void delete(String id) {
        read_db_file();

        for(int i = 0; i < this.db.size(); i++)
            if(this.db.get(i).id.equals(id))
                this.db.remove(i);
        
        write_db_file();
    }
}
