package app.src.list_of_courses;

import app.src.boundaries.CourseRemoverBoundary;
import app.src.entities.PersistenceGateway;

public class CourseRemover implements CourseRemoverBoundary {
    PersistenceGateway courses_db = null;

    public CourseRemover(PersistenceGateway courses_db) {
        this.courses_db = courses_db;
    }

    @Override
    public void remove_course(String id) {
        this.courses_db.delete(id);
    }
}
