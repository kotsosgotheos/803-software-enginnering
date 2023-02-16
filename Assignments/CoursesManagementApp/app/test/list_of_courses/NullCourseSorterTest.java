package app.test.list_of_courses;

import java.util.ArrayList;

import app.src.list_of_courses.CourseSorter;
import app.src.list_of_courses.NullCourseSorter;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.Identifiable;

import persistence.CustomGatewayImplementation;

import jspec.*;

public class NullCourseSorterTest extends SpecModule {
    private PersistenceGateway courses;

    public void spec_code() {
        before(() -> {
            this.courses = new CustomGatewayImplementation("Courses");
            this.courses.save(new Course(
                "Digital Design I",
                "Some info about ditital design 1",
                "Kavousianos",
                "2020",
                "3"
            ));
            this.courses.save(new Course(
                "Software Engineering",
                "More info about S. E.",
                "Zarras",
                "2022",
                "8"
            ));
            this.courses.save(new Course(
                "Operating Systems",
                "OS class information and syllabus",
                "Anastasiadis",
                "2021",
                "7"
            ));
        });

        describe("NullCourseSorter", () -> {
            it("creates a sorter object", () -> {
                CourseSorter null_sorter = new NullCourseSorter();
                assert_that(null_sorter).isnot(null);
            });

            it("does not sort the db in any way", () -> {
                CourseSorter null_sorter = new NullCourseSorter();
                ArrayList<Identifiable> sorted = null_sorter.sort(this.courses);

                Course anastasiadis = new Course("Operating Systems", "OS class information and syllabus", "Anastasiadis", "2021", "7");
                Course kavousianos = new Course("Digital Design I", "Some info about ditital design 1", "Kavousianos", "2020", "3");
                Course zarras = new Course("Software Engineering", "More info about S. E.", "Zarras", "2022", "8");

                assert_that(((Course)(sorted.get(0))).equals(kavousianos)).is(true);
                assert_that(((Course)(sorted.get(1))).equals(zarras)).is(true);
                assert_that(((Course)(sorted.get(2))).equals(anastasiadis)).is(true);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
