package client.cli;

import app.src.entities.DescriptiveStatisticsGateway;
import app.src.entities.PersistenceGateway;
import persistence.SqliteGatewayImplementation;
import statistics.ApacheMathDescriptiveStatisticsGatewayImplementation;

public class EntryPoint {
    static PersistenceGateway database = new SqliteGatewayImplementation("Database.db");
    static DescriptiveStatisticsGateway ds_engine = new ApacheMathDescriptiveStatisticsGatewayImplementation();

    public static void main(String args[]) {
        System.out.println("Hello, World!");
    }
}
