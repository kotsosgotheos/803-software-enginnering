package statistics;

import app.src.entities.DescriptiveStatisticsGateway;

public class NullDescriptiveStatisticsGatewayImplementation implements DescriptiveStatisticsGateway {
    @Override
    public double get_min(double dataset[]) {
        return 0;
    }

    @Override
    public double get_max(double dataset[]) {
        return 0;
    }

    @Override
    public double get_mean(double dataset[]) {
        return 0;
    }

    @Override
    public double get_standard_deviation(double dataset[]) {
        return 0;
    }

    @Override
    public double get_variance(double dataset[]) {
        return 0;
    }

    @Override
    public double get_skewness(double dataset[]) {
        return 0;
    }

    @Override
    public double get_median(double dataset[]) {
        return 0;
    }

    @Override
    public double get_kurtosis(double dataset[]) {
        return 0;
    }
}
