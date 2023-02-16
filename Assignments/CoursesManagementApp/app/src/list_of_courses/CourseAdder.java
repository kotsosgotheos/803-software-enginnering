package app.src.list_of_courses;

import app.src.boundaries.CourseAdderBoundary;
import app.src.entities.Course;
import app.src.entities.PersistenceGateway;

public class CourseAdder implements CourseAdderBoundary {
    PersistenceGateway courses_db = null;
    
    public CourseAdder(PersistenceGateway courses_db) {
        this.courses_db = courses_db;
    }

    @Override
    public void add_new_course(String name, String syllabus, String instructor, String year, String semester) {
        this.courses_db.save(new Course(
            name,
            syllabus,
            instructor,
            year,
            semester
        ));
    }
}
