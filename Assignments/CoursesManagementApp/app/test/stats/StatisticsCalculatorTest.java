package app.test.stats;

import java.util.HashMap;
import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.DescriptiveStatisticsGateway;
import app.src.entities.Identifiable;
import app.src.entities.StatisticsStrategy;
import app.src.entities.StudentRegistration;
import app.src.list_of_students.StudentAdder;
import app.src.grades.GradesAdder;
import app.src.grades.GradesCalculator;

import app.src.stats.StatisticsCalculator;
import app.src.stats.*;

import statistics.ApacheMathDescriptiveStatisticsGatewayImplementation;

import jspec.*;

public class StatisticsCalculatorTest extends SpecModule {
    private Course course;

    public void spec_code() {
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

        describe("StatisticsCalulator object", () -> {
            it("creates a StatisticsCalulator object", () -> {
                StatisticsCalculator calc = new StatisticsCalculator(this.course);
                assert_that(calc).isnot(null);
            });

            it("sets a field describing calculation strategies", () -> {
                StatisticsCalculator calc = new StatisticsCalculator(this.course);
                ArrayList<StatisticsStrategy> strategies = new ArrayList<StatisticsStrategy>();
                DescriptiveStatisticsGateway ds = new ApacheMathDescriptiveStatisticsGatewayImplementation();
                strategies.add(new KurtosisStatisticStrategy(ds));
                strategies.add(new MaxStatisticStrategy(ds));
                strategies.add(new MeanStatisticStrategy(ds));
                strategies.add(new MedianStatisticStrategy(ds));
                strategies.add(new MinStatisticStrategy(ds));
                strategies.add(new SkewnessStatisticStrategy(ds));
                strategies.add(new StandardDeviationStatisticStrategy(ds));
                strategies.add(new VarianceStatisticStrategy(ds));
                calc.set_strategies(strategies);

                assert_that(calc.strategies.size()).is(8);
            });

            it("creates a hashmap of all calculation results according to the strategy list", () -> {
                StatisticsCalculator calc = new StatisticsCalculator(this.course);
                ArrayList<StatisticsStrategy> strategies = new ArrayList<StatisticsStrategy>();
                DescriptiveStatisticsGateway ds = new ApacheMathDescriptiveStatisticsGatewayImplementation();
                strategies.add(new KurtosisStatisticStrategy(ds));
                strategies.add(new MaxStatisticStrategy(ds));
                strategies.add(new MeanStatisticStrategy(ds));
                strategies.add(new MedianStatisticStrategy(ds));
                strategies.add(new MinStatisticStrategy(ds));
                strategies.add(new SkewnessStatisticStrategy(ds));
                strategies.add(new StandardDeviationStatisticStrategy(ds));
                strategies.add(new VarianceStatisticStrategy(ds));
                calc.set_strategies(strategies);

                HashMap<String, Double> results = calc.calculate_statistics();
                assert_that(results.size()).is(8);
                assert_that(results.get("Kurtosis")).equals_to(0d/0d);
                assert_that(results.get("Skewness")).equals_to(1.688);
                assert_that(results.get("Max")).equals_to(9.85);
                assert_that(results.get("Mean")).equals_to(8.317);
                assert_that(results.get("Variance")).equals_to(1.773);
                assert_that(results.get("Standard Deviation")).equals_to(1.332);
                assert_that(results.get("Min")).equals_to(7.45);
                assert_that(results.get("Median")).equals_to(8.317);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
