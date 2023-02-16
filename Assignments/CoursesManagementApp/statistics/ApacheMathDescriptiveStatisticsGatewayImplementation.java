package statistics;

import statistics.apachecommons.ApacheMathDescriptive;

import app.src.entities.DescriptiveStatisticsGateway;

public class ApacheMathDescriptiveStatisticsGatewayImplementation implements DescriptiveStatisticsGateway {
    private ApacheMathDescriptive desc = null;

    private void set_dataset(double dataset[]) {
        this.desc = new ApacheMathDescriptive(dataset);
    }

    @Override
    public double get_min(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_min()));
    }

    @Override
    public double get_max(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_max()));
    }

    @Override
    public double get_mean(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_mean()));
    }

    @Override
    public double get_standard_deviation(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_standard_deviation()));
    }

    @Override
    public double get_variance(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_variance()));
    }

    @Override
    public double get_skewness(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_skewness()));
    }

    @Override
    public double get_median(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_median()));
    }

    @Override
    public double get_kurtosis(double dataset[]) {
        if(this.desc == null) set_dataset(dataset);
        return Double.parseDouble(String.format("%.3f", this.desc.get_kurtosis()));
    }
}
