package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.AlertCreatePort;
import codes.harsha.co2stat.application.port.out.AlertQueryPort;
import codes.harsha.co2stat.domain.Alert;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlertPersistenceAdapter implements AlertCreatePort, AlertQueryPort {

    private final AlertRepositoryMongo alertRepositoryMongo;

    public AlertPersistenceAdapter(AlertRepositoryMongo alertRepositoryMongo) {
        this.alertRepositoryMongo = alertRepositoryMongo;
    }

    @Override
    public void save(Alert alert) {
        alertRepositoryMongo.save(alert);
    }

    @Override
    public List<Alert> findAlerts(String sensorId) {
        return alertRepositoryMongo.findAllBySensorId(sensorId);
    }
}
