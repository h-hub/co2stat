package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.domain.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepositoryMongo extends MongoRepository<Alert, String> {
    List<Alert> findAllBySensorId(String sensorId);
}
