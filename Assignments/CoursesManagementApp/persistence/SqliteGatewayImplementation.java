package persistence;

import java.util.ArrayList;

import app.src.entities.PersistenceGateway;
import persistence.sqlite.src.SqliteApi;
import app.src.entities.Identifiable;

public class SqliteGatewayImplementation implements PersistenceGateway {
    private SqliteApi db;

    public SqliteGatewayImplementation(String dbname) {
        this.db = new SqliteApi(dbname);
    }

    @Override
    public void save(Identifiable item) {
        this.db.save(item);
    }

    @Override
    public Identifiable get_by_id(String id) {
        return this.db.get_by_id(id);
    }

    @Override
    public ArrayList<Identifiable> get_all_items() {
        return this.db.get_all_items();
    }

    @Override
    public void update(String id, Identifiable new_item) {
        this.db.update(id, new_item);
    }

    @Override
    public void delete(String id) {
        this.db.delete(id);
    }
}
