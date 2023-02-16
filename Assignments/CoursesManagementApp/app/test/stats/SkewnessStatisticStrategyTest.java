package app.test.stats;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Grades;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;
import app.src.list_of_students.StudentAdder;
import app.src.grades.GradesAdder;
import app.src.grades.GradesCalculator;
import app.src.stats.SkewnessStatisticStrategy;

import statistics.ApacheMathDescriptiveStatisticsGatewayImplementation;

import jspec.*;

public class SkewnessStatisticStrategyTest extends SpecModule {
    private Course course;

    public void spec_code() {
        describe("SkewnessStatisticStrategy", () -> {
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

            it("creates a SkewnessStatisticStrategy object", () -> {
                SkewnessStatisticStrategy skewness = new SkewnessStatisticStrategy(new ApacheMathDescriptiveStatisticsGatewayImplementation());
                assert_that(skewness).isnot(null);
            });

            it("calculates the skewness statistic out of a set of latest grades", () -> {
                SkewnessStatisticStrategy skewness = new SkewnessStatisticStrategy(new ApacheMathDescriptiveStatisticsGatewayImplementation());
                assert_that(skewness.calculate(this.course)).equals_to(1.688);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
