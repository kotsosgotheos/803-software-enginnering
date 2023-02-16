package app.src.list_of_students;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Identifiable;

public class NullStudentSorter implements StudentSorter {
    @Override
    public ArrayList<Identifiable> sort(Course course) {
        return course.get_students_db().get_all_items();
    }
}
