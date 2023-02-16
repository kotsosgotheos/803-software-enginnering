package app.src.grades;

import java.util.ArrayList;

import app.src.boundaries.GradesCalculatorBoundary;
import app.src.entities.Course;
import app.src.entities.Grades;
import app.src.entities.Identifiable;
import app.src.entities.StudentRegistration;

public class GradesCalculator implements GradesCalculatorBoundary {
    private Course course;

    public GradesCalculator(Course course) {
        this.course = course;
    }

    public ArrayList<Grades> get_latest_grades() {
        ArrayList<Grades> latest = new ArrayList<>();

        for(Identifiable student : this.course.get_students_db().get_all_items()) {
            ArrayList<Identifiable> grades = ((StudentRegistration)student).get_grades_db().get_all_items();

            if(grades.size() > 0)
                latest.add((Grades)grades.get(grades.size() - 1));
        }
        
        return latest;
    }

    @Override
    public double calculate_weighted_average(double project_weight) {
        ArrayList<Grades> latest = get_latest_grades();

        double sum = 0;
        for(Grades grades : latest)
            sum += project_weight * Double.parseDouble(grades.project) + (1-project_weight) * Double.parseDouble(grades.exam);
        
        return Double.parseDouble(String.format("%.3f", (sum / latest.size())));
    }
}
