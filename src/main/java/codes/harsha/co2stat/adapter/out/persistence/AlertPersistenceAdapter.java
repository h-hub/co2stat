package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.AlertCreatePort;
import codes.harsha.co2stat.domain.Alert;
import org.springframework.stereotype.Component;

@Component
public class AlertPersistenceAdapter implements AlertCreatePort {

    private final AlertRepositoryMongo alertRepositoryMongo;

    public AlertPersistenceAdapter(AlertRepositoryMongo alertRepositoryMongo) {
        this.alertRepositoryMongo = alertRepositoryMongo;
    }

    @Override
    public void save(Alert alert) {
        alertRepositoryMongo.save(alert);
    }
}
