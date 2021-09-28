package codes.harsha.co2stat.application.port.out;

import codes.harsha.co2stat.domain.Mesurement;

import java.time.ZonedDateTime;
import java.util.List;

public interface MesurementQueryPort {
    List<Mesurement> findLastMesurements(String sensorId, int mesurementCount);

    double getAverage(ZonedDateTime from, ZonedDateTime to, String sensorId);
}
