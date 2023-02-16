package app.test.entities;

import app.src.entities.Grades;

import jspec.*;

public class GradesTest extends SpecModule {
    Grades g = null;

    public void spec_code() {
        describe("Grades object", () -> {
            before_each(() -> {
                String project = "6.5";
                String exam = "5.2";
                String semester = "4";
                this.g = new Grades(project, exam, semester);
            });

            it("creates a random grades object with passed data", () -> {
                assert_that(g.project).equals_to("6.5");
                assert_that(g.exam).equals_to("5.2");
                assert_that(g.semester).equals_to("4");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
