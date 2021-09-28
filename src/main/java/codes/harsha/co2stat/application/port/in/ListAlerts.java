package codes.harsha.co2stat.application.port.in;

import codes.harsha.co2stat.domain.Alert;

import java.util.List;

public interface ListAlerts {
    List<Alert> getAlerts(String sensorId);
}
