package app.src.boundaries;

import java.util.ArrayList;
import java.util.HashMap;

import app.src.entities.StatisticsStrategy;

public interface StatisticsCalculatorBoundary {
    public void set_strategies(ArrayList<StatisticsStrategy> strategies);
    public HashMap<String, Double> calculate_statistics();
}
