package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.out.SensorCreatePort;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class SensorService implements CreateSensor {

    private final SensorCreatePort sensorCreatePort;

    public SensorService(SensorCreatePort sensorCreatePort) {
        this.sensorCreatePort = sensorCreatePort;
    }

    @Override
    public void create(String id) {
        Assert.notNull(id, "Sensor id cannot be null");
        Sensor sensor = new Sensor(id);
        sensorCreatePort.save(sensor);
    }

}
