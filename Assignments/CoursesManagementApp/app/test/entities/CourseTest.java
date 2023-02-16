package app.test.entities;

import app.src.entities.Course;
import jspec.*;

public class CourseTest extends SpecModule {
    private Course c = null;

    public void spec_code() {
        describe("Course object", () -> {
            before_each(() -> {
                String name = "Electronics";
                String syllabus = "Introduction to electronics. Amplifiers - Operational amplifiers. Semiconductor theory. The p-n junction - Diodes. Diode circuits (rectifier and limiting circuits). Field effect transistors and bipolar junction transistors: a) physical structure and operation, b) current-voltage characteristics, c) DC operation - bias, d) small-signal equivalent circuit models. Single-stage transistor amplifiers: biasing and operation. Differential amplifiers. Multi-stage amplifiers. Frequency response. Feedback.";
                String instructor = "Tsiatoyhas";
                String year = "2022";
                String semester = "4";
                
                this.c = new Course(name, syllabus, instructor, year, semester);
            });

            it("creates a random course object with passed data", () -> {
                assert_that(c.name).equals_to("Electronics");
                assert_that(c.syllabus).equals_to("Introduction to electronics. Amplifiers - Operational amplifiers. Semiconductor theory. The p-n junction - Diodes. Diode circuits (rectifier and limiting circuits). Field effect transistors and bipolar junction transistors: a) physical structure and operation, b) current-voltage characteristics, c) DC operation - bias, d) small-signal equivalent circuit models. Single-stage transistor amplifiers: biasing and operation. Differential amplifiers. Multi-stage amplifiers. Frequency response. Feedback.");
                assert_that(c.instructor).equals_to("Tsiatoyhas");
                assert_that(c.year).equals_to("2022");
                assert_that(c.semester).equals_to("4");
            });

            it("ensures that a new course object has no students registered", () -> {
                assert_that(c.get_students_db().get_all_items().size()).equals_to(0);
            });

            after(() -> {
                try {
                    /* TODO Figure out DB pathing */
                    String currentPath = new java.io.File(".").getCanonicalPath();
                    String nix = currentPath + "/persistence/sqlite/src/Database.db";
                    String win = currentPath + "\\persistence\\sqlite\\src\\Database.db";
                    new java.io.File(nix).delete();
                    new java.io.File(win).delete();
                }
                catch(Exception e) {}
            });
        });
    }
}
