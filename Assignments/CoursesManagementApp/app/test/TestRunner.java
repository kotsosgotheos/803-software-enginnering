package app.test;

import jspec.*;

import app.test.entities.*;
import app.test.grades.*;
import app.test.list_of_courses.*;
import app.test.list_of_students.*;
import app.test.login.*;
import app.test.stats.*;

public class TestRunner {
    public static void main(String args[]) {
        SpecModule modules[] = {
            new CourseTest(),
            new GradesTest(),
            new RegistrationTokenTest(),
            new StudentRegistrationTest(),

            new GradesAdderTest(),
            new GradesCalculatorTest(),

            new CourseAdderTest(),
            new CourseBrowserTest(),
            new CourseRemoverTest(),
            new CourseUpdaterTest(),
            new InstructorCourseSorterTest(),
            new NameCourseSorterTest(),
            new NullCourseSorterTest(),
            new SemesterCourseSorterTest(),
            new SyllabusCourseSorterTest(),
            new YearCourseSorterTest(),
            
            new NameStudentSorterTest(),
            new NullStudentSorterTest(),
            new SemesterStudentSorterTest(),
            new StudentAdderTest(),
            new StudentBrowserTest(),
            new StudentRemoverTest(),
            new StudentUpdaterTest(),
            new YearOfRegistrationStudentSorterTest(),

            new ProfessorLoginTest(),
            new ProfessorRegistrationTest(),

            new KurtosisStatisticStrategyTest(),
            new MaxStatisticStrategyTest(),
            new MeanStatisticStrategyTest(),
            new MedianStatisticStrategyTest(),
            new MinStatisticStrategyTest(),
            new SkewnessStatisticStrategyTest(),
            new StandardDeviationStatisticStrategyTest(),
            new VarianceStatisticStrategyTest(),
            new StatisticsCalculatorTest(),
        };
        Spec s = new Spec(modules);
        s.run_spec_suite("all");
    }
}
