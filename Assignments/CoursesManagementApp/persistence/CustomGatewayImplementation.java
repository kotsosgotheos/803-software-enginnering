package persistence;

import java.util.ArrayList;

import app.src.entities.PersistenceGateway;
import app.src.entities.Identifiable;
import persistence.custom.src.CustomDB;

public class CustomGatewayImplementation implements PersistenceGateway {
    private CustomDB db;

    public CustomGatewayImplementation(String dbname) {
        this.db = new CustomDB(dbname);
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
