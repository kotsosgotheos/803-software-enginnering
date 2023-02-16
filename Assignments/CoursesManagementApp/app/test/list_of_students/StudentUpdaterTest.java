package app.test.list_of_students;

import app.src.entities.Course;
import app.src.entities.StudentRegistration;

import app.src.list_of_students.StudentUpdater;

import jspec.*;

public class StudentUpdaterTest extends SpecModule {
    private Course test = null;
    private StudentRegistration athpap;
    private StudentRegistration athkour;
    private StudentRegistration kongeo;

    public void spec_code() {
        describe("StudentUpdater object", () -> {
            before(() -> {
                this.test = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year", "semester"
                );

                this.athpap = new StudentRegistration(
                    "Ath Pap",
                    "2017",
                    "10"
                );
                this.athkour = new StudentRegistration(
                    "Ath Kour",
                    "2018",
                    "8"
                );
                this.kongeo = new StudentRegistration(
                    "Kon Geo",
                    "2018",
                    "8"
                );

                this.test.get_students_db().save(this.athpap);
                this.test.get_students_db().save(this.athkour);
                this.test.get_students_db().save(this.kongeo);
            });

            it("creates a student updater object", () -> {
                StudentUpdater updater = new StudentUpdater(this.test, this.athpap.id);
                assert_that(updater).isnot(null);
            });

            it("updates the name of a specific student", () -> {
                StudentUpdater updater = new StudentUpdater(this.test, this.athpap.id);
                updater
                    .edit_name("edited name")
                    .update();
                
                StudentRegistration st = (StudentRegistration)this.test.get_students_db().get_all_items().get(0);
                assert_that(st.name).equals_to("edited name");
            });

            it("updates all fields using the builder pattern", () -> {
                StudentUpdater updater = new StudentUpdater(this.test, this.kongeo.id);
                updater
                    .edit_name("new name")
                    .edit_year_of_registration("new year")
                    .edit_semester("new semester")
                    .update();
                
                StudentRegistration st = (StudentRegistration)this.test.get_students_db().get_all_items().get(2);
                assert_that(st.name).equals_to("new name");
                assert_that(st.year_of_registration).equals_to("new year");
                assert_that(st.semester).equals_to("new semester");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
