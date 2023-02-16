package app.src.entities;

import java.util.ArrayList;

public interface PersistenceGateway {
    public void save(Identifiable item);
    public Identifiable get_by_id(String id);
    public ArrayList<Identifiable> get_all_items();
    public void update(String id, Identifiable new_item);
    public void delete(String id);
}
