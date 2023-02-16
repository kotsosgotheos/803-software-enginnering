package app.src.list_of_students;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Identifiable;

public interface StudentSorter {
    public ArrayList<Identifiable> sort(Course course);
}
