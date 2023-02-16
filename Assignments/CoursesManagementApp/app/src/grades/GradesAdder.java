package app.src.grades;

import app.src.boundaries.GradesAdderBoundary;
import app.src.entities.Grades;
import app.src.entities.StudentRegistration;

public class GradesAdder implements GradesAdderBoundary {
    private StudentRegistration reg;

    public GradesAdder(StudentRegistration reg) {
        this.reg = reg;
    }

    @Override
    public void add_new_grades(String project, String exam, String semester) {
        this.reg.get_grades_db().save(new Grades(
            project,
            exam,
            semester
        ));
    }
}
