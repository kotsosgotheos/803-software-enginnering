package app.src.stats;

import java.util.ArrayList;
import java.util.HashMap;

import app.src.boundaries.StatisticsCalculatorBoundary;
import app.src.entities.Course;
import app.src.entities.StatisticsStrategy;

public class StatisticsCalculator implements StatisticsCalculatorBoundary {
    public ArrayList<StatisticsStrategy> strategies = null;
    public Course course;

    public StatisticsCalculator(Course course) {
        this.course = course;
    }

    @Override
    public void set_strategies(ArrayList<StatisticsStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public HashMap<String, Double> calculate_statistics() {
        HashMap<String, Double> stats = new HashMap<String, Double>();

        for(StatisticsStrategy strategy : this.strategies)
            stats.put(strategy.toString(), strategy.calculate(this.course));
        
        return stats;
    }
}
