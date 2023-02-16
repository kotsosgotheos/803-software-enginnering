package app.test.list_of_courses;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;

import app.src.list_of_courses.CourseRemover;

import persistence.CustomGatewayImplementation;

import jspec.*;

public class CourseRemoverTest extends SpecModule {
    private PersistenceGateway courses;
    private Course anastasiadis;
    private Course kavousianos;
    private Course zarras;

    public void spec_code() {
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

        describe("Remover object", () -> {
            it("creates a new Remover object", () -> {
                CourseRemover rem = new CourseRemover(this.courses);
                assert_that(rem).isnot(null);
            });

            it("removes a course from the course list", () -> {
                CourseRemover rem = new CourseRemover(this.courses);
                rem.remove_course(this.zarras.id);

                assert_that(this.courses.get_all_items().size()).equals_to(2);

                Course first_course = (Course)this.courses.get_all_items().get(0);
                assert_that(first_course.equals(this.kavousianos)).is(true);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
