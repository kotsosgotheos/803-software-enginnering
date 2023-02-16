package app.src.list_of_students;

import app.src.boundaries.StudentRemoverBoundary;
import app.src.entities.Course;

public class StudentRemover implements StudentRemoverBoundary {
    private Course course;

    public StudentRemover(Course course) {
        this.course = course;
    }

    @Override
    public void remove_student(String id) {
        this.course.get_students_db().delete(id);
    }
}
