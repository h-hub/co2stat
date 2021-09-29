package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.in.GetSensorStatus;
import codes.harsha.co2stat.application.port.out.SensorCreatePort;
import codes.harsha.co2stat.application.port.out.SensorQueryPort;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class SensorService implements CreateSensor, GetSensorStatus {

    private final SensorCreatePort sensorCreatePort;

    private final SensorQueryPort sensorQueryPort;

    public SensorService(SensorCreatePort sensorCreatePort, SensorQueryPort sensorQueryPort) {
        this.sensorCreatePort = sensorCreatePort;
        this.sensorQueryPort = sensorQueryPort;
    }

    @Override
    public void create(String id) {
        Assert.notNull(id, "Sensor ID is required");
        Sensor sensor = new Sensor(id);
        sensorCreatePort.save(sensor);
    }

    @Override
    public String getStatus(String sensorId) {
        Assert.notNull(sensorId, "Sensor ID is required");
        Sensor.Status status = sensorQueryPort.find(sensorId).getStatus();
        if(status==null){
            return "";
        }
        return status.getStr();
    }
}
