package app.src.entities;

public interface DescriptiveStatisticsGateway {
    public double get_min(double dataset[]);
    public double get_max(double dataset[]);
    public double get_mean(double dataset[]);
    public double get_standard_deviation(double dataset[]);
    public double get_variance(double dataset[]);
    public double get_skewness(double dataset[]);
    public double get_median(double dataset[]);
    public double get_kurtosis(double dataset[]);
}
