package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.ListAlerts;
import codes.harsha.co2stat.application.port.out.AlertQueryPort;
import codes.harsha.co2stat.domain.Alert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class AlertService implements ListAlerts {

    private final AlertQueryPort alertQueryPort;

    public AlertService(AlertQueryPort alertQueryPort) {
        this.alertQueryPort = alertQueryPort;
    }

    @Override
    public List<Alert> getAlerts(String sensorId) {
        Assert.notNull(sensorId, "Sensor ID is required");

        return alertQueryPort.findAlerts(sensorId);
    }
}
