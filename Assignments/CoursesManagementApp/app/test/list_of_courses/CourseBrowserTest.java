package app.test.list_of_courses;

import java.util.ArrayList;

import app.src.list_of_courses.CourseBrowser;
import app.src.list_of_courses.InstructorCourseSorter;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.Identifiable;

import persistence.CustomGatewayImplementation;

import jspec.*;

public class CourseBrowserTest extends SpecModule {
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

        describe("ListOfCoursesBrowser", () -> {
            it("creates a list browser object", () -> {
                CourseBrowser browser = new CourseBrowser(
                    this.courses,
                    new InstructorCourseSorter()
                );

                assert_that(browser).isnot(null);
            });

            it("returns a list of courses sorted according to the injected sorter", () -> {
                CourseBrowser browser = new CourseBrowser(
                    this.courses,
                    new InstructorCourseSorter()
                );

                ArrayList<Identifiable> list = browser.list_courses();
                assert_that(((Course)(list.get(0))).equals(this.anastasiadis)).is(true);
                assert_that(((Course)(list.get(1))).equals(this.kavousianos)).is(true);
                assert_that(((Course)(list.get(2))).equals(this.zarras)).is(true);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
