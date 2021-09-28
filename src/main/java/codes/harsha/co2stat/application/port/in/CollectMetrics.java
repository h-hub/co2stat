package codes.harsha.co2stat.application.port.in;

public interface CollectMetrics {
    double getMaxLast30Days(String sensorId);
    double getAvgLast30Days(String sensorId);
}
