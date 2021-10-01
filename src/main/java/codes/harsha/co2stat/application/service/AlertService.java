package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.ListAlerts;
import codes.harsha.co2stat.application.port.out.AlertQueryPort;
import codes.harsha.co2stat.application.port.out.SensorQueryPort;
import codes.harsha.co2stat.domain.Alert;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class AlertService implements ListAlerts {

    private final AlertQueryPort alertQueryPort;

    private final SensorQueryPort sensorQueryPort;

    @Autowired
    public AlertService(AlertQueryPort alertQueryPort, SensorQueryPort sensorQueryPort) {
        this.sensorQueryPort = sensorQueryPort;
        this.alertQueryPort = alertQueryPort;
    }

    @Override
    public List<Alert> getAlerts(String sensorId) {
        Assert.notNull(sensorId, "Sensor ID is required");
        Sensor sensor = sensorQueryPort.find(sensorId);
        return alertQueryPort.findAlerts(sensorId);
    }
}
