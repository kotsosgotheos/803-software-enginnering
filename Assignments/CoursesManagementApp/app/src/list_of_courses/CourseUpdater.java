package app.src.list_of_courses;

import app.src.boundaries.CourseUpdaterBoundary;
import app.src.entities.Course;
import app.src.entities.PersistenceGateway;

public class CourseUpdater implements CourseUpdaterBoundary {
    private PersistenceGateway courses_db;
    private String id;
    private Course current_edited_course;

    public CourseUpdater(PersistenceGateway courses_db, String id) {
        this.courses_db = courses_db;
        this.id = id;
        this.current_edited_course = (Course)this.courses_db.get_by_id(id);
    }

    public CourseUpdater edit_name(String new_name) {
        this.current_edited_course.name = new_name;
        return this;
    }

    public CourseUpdater edit_syllabus(String new_syllabus) {
        this.current_edited_course.syllabus = new_syllabus;
        return this;
    }

    public CourseUpdater edit_instructor(String new_instructor) {
        this.current_edited_course.instructor = new_instructor;
        return this;
    }

    public CourseUpdater edit_year(String new_year) {
        this.current_edited_course.year = new_year;
        return this;
    }

    public CourseUpdater edit_semester(String new_semester) {
        this.current_edited_course.semester = new_semester;
        return this;
    }

    @Override
    public void update() {
        this.courses_db.update(
            this.id,
            this.current_edited_course
        );
    }
}
