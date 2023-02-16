package app.src.list_of_courses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.Identifiable;

public class SyllabusCourseSorter implements CourseSorter {
    @Override
    public ArrayList<Identifiable> sort(PersistenceGateway db) {
        ArrayList<Identifiable> sorted = db.get_all_items();

        Collections.sort(sorted, new Comparator<Identifiable>() {
            public int compare(Identifiable s1, Identifiable s2) {
                Course c1 = (Course)s1;
                Course c2 = (Course)s2;
                return c1.compare_syllabi(c2);
            }
        });

        return sorted;
    }
}
