package app.test.entities;

import app.src.entities.StudentRegistration;
import jspec.*;

public class StudentRegistrationTest extends SpecModule {
    private StudentRegistration r = null;

    public void spec_code() {
        describe("StudentRegistration object", () -> {
            before_each(() -> {
                String name = "Oblivious";
                String year_of_registration = "2021";
                String semester = "8";

                this.r = new StudentRegistration(name, year_of_registration, semester);
            });

            it("creates a random student object with passed data", () -> {
                assert_that(r.name).equals_to("Oblivious");
                assert_that(r.year_of_registration).equals_to("2021");
                assert_that(r.semester).equals_to("8");
            });

            it("ensures that a new student registration object has no grades registered", () -> {
                assert_that(r.get_grades_db().get_all_items().size()).equals_to(0);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
