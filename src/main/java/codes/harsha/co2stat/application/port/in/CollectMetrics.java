package codes.harsha.co2stat.application.port.in;

public interface CollectMetrics {
    int getMaxLast30Days(String sensorId);
    int getAvgLast30Days(String sensorId);
}
