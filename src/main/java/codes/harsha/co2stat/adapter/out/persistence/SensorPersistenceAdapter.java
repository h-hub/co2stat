package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.SensorCreatePort;
import codes.harsha.co2stat.application.port.out.SensorQueryPort;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorPersistenceAdapter implements SensorCreatePort, SensorQueryPort {

    private final SensorRepositoryMongo sensorRepositoryMongo;

    public SensorPersistenceAdapter(SensorRepositoryMongo sensorRepositoryMongo) {
        this.sensorRepositoryMongo = sensorRepositoryMongo;
    }

    @Override
    public void save(Sensor sensor) {
        sensorRepositoryMongo.save(sensor);
    }

    @Override
    public Sensor find(String id) {
        return sensorRepositoryMongo.findById(id).orElseThrow();
    }
}
