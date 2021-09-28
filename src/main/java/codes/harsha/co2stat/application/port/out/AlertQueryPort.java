package codes.harsha.co2stat.application.port.out;

import codes.harsha.co2stat.domain.Alert;

import java.util.List;

public interface AlertQueryPort {
    List<Alert> findAlerts(String sensorId);
}
