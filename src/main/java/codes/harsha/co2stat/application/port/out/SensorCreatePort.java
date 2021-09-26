package codes.harsha.co2stat.application.port.out;

import codes.harsha.co2stat.domain.Sensor;

public interface SensorCreatePort {
    void save(Sensor sensor);
}
