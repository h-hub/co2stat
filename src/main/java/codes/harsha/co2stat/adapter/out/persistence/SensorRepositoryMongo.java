package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.domain.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepositoryMongo extends MongoRepository<Sensor, String> {
}
