package app.test.list_of_students;

import app.src.entities.Course;
import app.src.entities.StudentRegistration;

import app.src.list_of_students.StudentRemover;

import jspec.*;

public class StudentRemoverTest extends SpecModule {
    private Course test = null;
    private String del_id;

    public void spec_code() {
        describe("StudentRemover object", () -> {
            before(() -> {
                this.test = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year", "semester"
                );

                StudentRegistration kour = new StudentRegistration(
                    "Ath Kour",
                    "2018",
                    "8"
                );
                this.del_id = kour.id;

                this.test.get_students_db().save(new StudentRegistration(
                    "Ath Pap",
                    "2017",
                    "10"
                ));
                this.test.get_students_db().save(kour);
                this.test.get_students_db().save(new StudentRegistration(
                    "Kon Geo",
                    "2018",
                    "8"
                ));
            });

            it("creates a student remover object", () -> {
                StudentRemover remover = new StudentRemover(this.test);
                assert_that(remover).isnot(null);
            });

            it("removes a student from the list", () -> {
                StudentRemover remover = new StudentRemover(this.test);
                remover.remove_student(del_id);

                assert_that(this.test.get_students_db().get_all_items().size()).equals_to(2);
                assert_that(((StudentRegistration)(this.test.get_students_db().get_all_items().get(0))).name).equals_to("Ath Pap");
                assert_that(((StudentRegistration)(this.test.get_students_db().get_all_items().get(1))).name).equals_to("Kon Geo");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
