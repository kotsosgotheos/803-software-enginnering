package app.src.entities;

import java.util.ArrayList;
import app.src.grades.GradesCalculator;

public abstract class StatisticsStrategy {
    public double[] grades_to_double_array(Course course) {
        GradesCalculator calc = new GradesCalculator(course);
        ArrayList<Grades> latest = calc.get_latest_grades();

        /* TODO Inject project_weight in the constructor of GradesCalculator */
        double project_weight = 0.3;
        double grades_doubles[] = new double[latest.size()];
        for(int i = 0; i < latest.size(); i++)
            grades_doubles[i] =
                Double.parseDouble(String.format("%.3f",
                    project_weight * Double.parseDouble(latest.get(i).project)
                  + (1-project_weight) * Double.parseDouble(latest.get(i).exam)));
        return grades_doubles;
    }

    public abstract double calculate(Course course);
    public abstract String toString();
}
