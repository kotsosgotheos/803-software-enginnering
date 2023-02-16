package app.test.list_of_students;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;

import app.src.list_of_students.StudentSorter;
import app.src.list_of_students.SemesterStudentSorter;

import jspec.*;

public class SemesterStudentSorterTest extends SpecModule {
    private Course course;

    public void spec_code() {
        describe("SemesterStudentSorter object", () -> {
            before(() -> {
                this.course = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year", "semester"
                );

                this.course.get_students_db().save(new StudentRegistration(
                    "Papapostolou",
                    "2017",
                    "10"
                ));
                this.course.get_students_db().save(new StudentRegistration(
                    "Koureas",
                    "2018",
                    "8"
                ));
                this.course.get_students_db().save(new StudentRegistration(
                    "Georgiou",
                    "2019",
                    "6"
                ));
            });

            it("creates a sorter object", () -> {
                StudentSorter semester_sorter = new SemesterStudentSorter();
                assert_that(semester_sorter).isnot(null);
            });

            it("sorts the student list by semester", () -> {
                StudentSorter semester_sorter = new SemesterStudentSorter();
                ArrayList<Identifiable> sorted = semester_sorter.sort(this.course);

                assert_that(((StudentRegistration)(sorted.get(0))).name).equals_to("Georgiou");
                assert_that(((StudentRegistration)(sorted.get(1))).name).equals_to("Koureas");
                assert_that(((StudentRegistration)(sorted.get(2))).name).equals_to("Papapostolou");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
