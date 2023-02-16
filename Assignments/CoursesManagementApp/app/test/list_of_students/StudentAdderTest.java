package app.test.list_of_students;

import app.src.entities.Course;
import app.src.entities.StudentRegistration;

import app.src.list_of_students.StudentAdder;

import jspec.*;

public class StudentAdderTest extends SpecModule {
    private Course test;

    public void spec_code() {
        describe("StudentAdder object", () -> {
            before(() -> {
                this.test = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year", "semester"
                );

                this.test.get_students_db().save(new StudentRegistration(
                    "Ath Pap",
                    "2017",
                    "10"
                ));
                this.test.get_students_db().save(new StudentRegistration(
                    "Ath Kour",
                    "2018",
                    "8"
                ));
                this.test.get_students_db().save(new StudentRegistration(
                    "Kon Geo",
                    "2018",
                    "8"
                ));
            });

            it("creates a student adder object", () -> {
                StudentAdder adder = new StudentAdder(this.test);
                assert_that(adder).isnot(null);
            });

            it("adds a new object to the list of students", () -> {
                StudentAdder adder = new StudentAdder(this.test);
                adder.add_new_student(
                    "Test Name",
                    "2014",
                    "17"
                );

                assert_that(this.test.get_students_db().get_all_items().size()).equals_to(4);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
