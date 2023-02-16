package app.src.list_of_students;

import java.util.ArrayList;

import app.src.boundaries.StudentBrowserBoundary;
import app.src.entities.Course;
import app.src.entities.Identifiable;

public class StudentBrowser implements StudentBrowserBoundary {
    private Course course;
    private StudentSorter sorter;

    public StudentBrowser(Course course, StudentSorter sorter) {
        this.course = course;
        this.sorter = sorter;
    }

    @Override
    public ArrayList<Identifiable> list_students() {
        return this.sorter.sort(this.course);
    }
}
