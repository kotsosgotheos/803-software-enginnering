package app.src.list_of_courses;

import java.util.ArrayList;

import app.src.entities.PersistenceGateway;
import app.src.entities.Identifiable;

public interface CourseSorter {
    public ArrayList<Identifiable> sort(PersistenceGateway db);
}
