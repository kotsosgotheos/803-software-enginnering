package app.test.list_of_students;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;

import app.src.list_of_students.NameStudentSorter;
import app.src.list_of_students.StudentBrowser;

import jspec.*;

public class StudentBrowserTest extends SpecModule {
    private Course test = null;

    public void spec_code() {
        describe("StudentBrowser object", () -> {
            before(() -> {
                this.test = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year", "semester"
                );

                this.test.get_students_db().save(new StudentRegistration(
                    "Papapostolou",
                    "2017",
                    "10"
                ));
                this.test.get_students_db().save(new StudentRegistration(
                    "Koureas",
                    "2018",
                    "8"
                ));
                this.test.get_students_db().save(new StudentRegistration(
                    "Georgiou",
                    "2018",
                    "8"
                ));
            });

            it("creates a student browser object", () -> {
                StudentBrowser browser = new StudentBrowser(
                    this.test,
                    new NameStudentSorter()
                );

                assert_that(browser).isnot(null);
            });

            it("returns a list of all students in a particular course", () -> {
                StudentBrowser browser = new StudentBrowser(
                    this.test,
                    new NameStudentSorter()
                );

                ArrayList<Identifiable> list = browser.list_students();
                assert_that(((StudentRegistration)(list.get(0))).name).equals_to("Georgiou");
                assert_that(((StudentRegistration)(list.get(1))).name).equals_to("Koureas");
                assert_that(((StudentRegistration)(list.get(2))).name).equals_to("Papapostolou");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
