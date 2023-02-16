package statistics.apachecommons;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class ApacheMathDescriptive {
    private DescriptiveStatistics ds = null;

    public ApacheMathDescriptive(double dataset[]) {
        this.ds = new DescriptiveStatistics(dataset);
    }

    public double get_min() {
        return this.ds.getMin();
    }

    public double get_max() {
        return this.ds.getMax();
    }

    public double get_mean() {
        return this.ds.getMean();
    }

    public double get_standard_deviation() {
        return this.ds.getStandardDeviation();
    }

    public double get_variance() {
        return this.ds.getVariance();
    }

    public double get_skewness() {
        return this.ds.getSkewness();
    }

    public double get_median() {
        return this.get_mean();
    }

    public double get_kurtosis() {
        return this.ds.getKurtosis();
    }
}
