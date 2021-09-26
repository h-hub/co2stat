package codes.harsha.co2stat.application.port.in;

import java.time.ZonedDateTime;

public interface CollectMesurement {
    void collect(String sensorId, int co2Level, String dateTime);
}
