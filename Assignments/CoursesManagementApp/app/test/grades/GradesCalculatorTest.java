package app.test.grades;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Grades;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;
import app.src.list_of_students.StudentAdder;
import app.src.grades.GradesAdder;
import app.src.grades.GradesCalculator;

import jspec.*;

public class GradesCalculatorTest extends SpecModule {
    private Course course;

    public void spec_code() {
        describe("GradesCalculator object", () -> {
            before(() -> {
                this.course = new Course(
                    "name",
                    "syllabus",
                    "instructor",
                    "year",
                    "semester"
                );

                StudentAdder student_adder = new StudentAdder(this.course);
                student_adder.add_new_student(
                    "Ath Pap",
                    "2017",
                    "10"
                );
                student_adder.add_new_student(
                    "Ath Kour",
                    "2018",
                    "8"
                );
                student_adder.add_new_student(
                    "Kon Geo",
                    "2018",
                    "8"
                );

                ArrayList<Identifiable> students = this.course.get_students_db().get_all_items();

                GradesAdder grades_adder0 = new GradesAdder((StudentRegistration)students.get(0));
                GradesAdder grades_adder1 = new GradesAdder((StudentRegistration)students.get(1));
                GradesAdder grades_adder2 = new GradesAdder((StudentRegistration)students.get(2));
                grades_adder0.add_new_grades("8.0", "2.5", "6");
                grades_adder0.add_new_grades("8.0", "7.5", "8");
                grades_adder1.add_new_grades("5.0", "8.5", "8");
                grades_adder2.add_new_grades("9.5", "10.0", "8");
            });

            it("creates a grades calculator object", () -> {
                GradesCalculator calc = new GradesCalculator(this.course);
                assert_that(calc).isnot(null);
            });

            it("ensures that iterating over grades only keeps the latest semester", () -> {
                GradesCalculator calc = new GradesCalculator(this.course);
                ArrayList<Grades> all_latest_grades = calc.get_latest_grades();
                Grades g0 = (Grades)all_latest_grades.get(0);
                assert_that(g0.semester).equals_to("8");
                assert_that(g0.project).equals_to("8.0");

                Grades g1 = (Grades)all_latest_grades.get(1);
                assert_that(g1.exam).equals_to("8.5");
            });

            it("calculates the weighted average of grades giving 30% to the project", () -> {
                GradesCalculator calc = new GradesCalculator(this.course);
                double project_weight = 0.3;
                double weighted_average = calc.calculate_weighted_average(project_weight);

                assert_that(weighted_average).equals_to(8.317);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
