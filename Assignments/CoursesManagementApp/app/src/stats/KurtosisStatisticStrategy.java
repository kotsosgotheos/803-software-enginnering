package app.src.stats;

import app.src.entities.Course;
import app.src.entities.DescriptiveStatisticsGateway;
import app.src.entities.StatisticsStrategy;

public class KurtosisStatisticStrategy extends StatisticsStrategy {
    private DescriptiveStatisticsGateway ds = null;

    public KurtosisStatisticStrategy(DescriptiveStatisticsGateway ds) {
        this.ds = ds;
    }

    @Override
    public double calculate(Course course) {
        return this.ds.get_kurtosis(this.grades_to_double_array(course));
    }

    @Override
    public String toString() {
        return "Kurtosis";
    }
}
