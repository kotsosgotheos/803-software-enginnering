package app.test.list_of_courses;

import app.src.list_of_courses.CourseUpdater;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;

import persistence.CustomGatewayImplementation;

import jspec.*;

public class CourseUpdaterTest extends SpecModule {
    private PersistenceGateway courses;
    private Course anastasiadis;
    private Course kavousianos;
    private Course zarras;

    public void spec_code() {
        describe("Updater object", () -> {
            before(() -> {
                this.kavousianos = new Course(
                    "Digital Design I",
                    "Some info about ditital design 1",
                    "Kavousianos",
                    "2020",
                    "3"
                );
                this.zarras = new Course(
                    "Software Engineering",
                    "More info about S. E.",
                    "Zarras",
                    "2022",
                    "8"
                );
                this.anastasiadis = new Course(
                    "Operating Systems",
                    "OS class information and syllabus",
                    "Anastasiadis",
                    "2021",
                    "7"
                );
    
                this.courses = new CustomGatewayImplementation("Courses");
                this.courses.save(kavousianos);
                this.courses.save(zarras);
                this.courses.save(anastasiadis);
            });

            it("creates an updater object", () -> {
                CourseUpdater up = new CourseUpdater(this.courses, this.zarras.id);
                assert_that(up).isnot(null);
            });

            it("updates the syllabus of a specific course", () -> {
                CourseUpdater up = new CourseUpdater(this.courses, this.zarras.id);
                up
                    .edit_syllabus("new syllabus")
                    .update();
                
                Course updated_course = (Course)this.courses.get_by_id(this.zarras.id);
                assert_that(updated_course.syllabus).equals_to("new syllabus");
            });

            it("updates the name of a specific course", () -> {
                CourseUpdater up = new CourseUpdater(this.courses, this.zarras.id);
                up
                    .edit_name("new name")
                    .update();
                
                Course updated_course = (Course)this.courses.get_by_id(this.zarras.id);
                assert_that(updated_course.name).equals_to("new name");
            });

            it("updates all fields using builder pattern", () -> {
                CourseUpdater up = new CourseUpdater(this.courses, this.zarras.id);
                up
                    .edit_instructor("new instructor")
                    .edit_name("new name")
                    .edit_semester("new semester")
                    .edit_syllabus("new syllabus")
                    .edit_year("new year")
                    .update();
                
                Course updated_course = (Course)this.courses.get_by_id(this.zarras.id);
                assert_that(updated_course.name).equals_to("new name");
                assert_that(updated_course.syllabus).equals_to("new syllabus");
                assert_that(updated_course.instructor).equals_to("new instructor");
                assert_that(updated_course.year).equals_to("new year");
                assert_that(updated_course.semester).equals_to("new semester");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
