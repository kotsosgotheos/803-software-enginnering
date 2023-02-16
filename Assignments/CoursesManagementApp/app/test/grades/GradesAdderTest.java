package app.test.grades;

import app.src.entities.Grades;
import app.src.entities.StudentRegistration;

import app.src.grades.GradesAdder;

import jspec.*;

public class GradesAdderTest extends SpecModule {
    private StudentRegistration reg;

    public void spec_code() {
        describe("GradesAdder object", () -> {
            before(() -> {
                this.reg = new StudentRegistration(
                    "Ath Pap",
                    "2017",
                    "10"
                );

                this.reg.get_grades_db().save(new Grades(
                    "8.0",
                    "2.5", /* Failing */
                    "4"
                ));

                this.reg.get_grades_db().save(new Grades(
                    "8.0",
                    "3.5", /* Failing */
                    "6"
                ));

                this.reg.get_grades_db().save(new Grades(
                    "8.0",
                    "4.5", /* TODO Add colors for passing/failing students */
                    "8"
                ));
            });
            
            it("creates a grades adder object", () -> {
                GradesAdder adder = new GradesAdder(this.reg);
                assert_that(adder).isnot(null);
            });

            it("adds a new object to the list of grades", () -> {
                GradesAdder adder = new GradesAdder(this.reg);
                adder.add_new_grades(
                    "8.0",
                    "6.0",
                    "10"
                );

                assert_that(this.reg.get_grades_db().get_all_items().size()).equals_to(4);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
