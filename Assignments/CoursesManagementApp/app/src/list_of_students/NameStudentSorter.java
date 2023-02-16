package app.src.list_of_students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import app.src.entities.Course;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;

public class NameStudentSorter implements StudentSorter {
    @Override
    public ArrayList<Identifiable> sort(Course course) {
        ArrayList<Identifiable> sorted = course.get_students_db().get_all_items();
        
        Collections.sort(sorted, new Comparator<Identifiable>() {
            public int compare(Identifiable s1, Identifiable s2) {
                StudentRegistration r1 = (StudentRegistration)s1;
                StudentRegistration r2 = (StudentRegistration)s2;
                return r1.compare_names(r2);
            }
        });

        return sorted;
    }
}
