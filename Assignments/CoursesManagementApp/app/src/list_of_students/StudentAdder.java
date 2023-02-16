package app.src.list_of_students;

import app.src.boundaries.StudentAdderBoundary;
import app.src.entities.Course;
import app.src.entities.StudentRegistration;

public class StudentAdder implements StudentAdderBoundary {
    private Course course;

    public StudentAdder(Course course) {
        this.course = course;
    }

    @Override
    public void add_new_student(String name, String year_of_registration, String semester) {
        this.course.get_students_db().save(new StudentRegistration(
            name,
            year_of_registration,
            semester
        ));
    }
}
