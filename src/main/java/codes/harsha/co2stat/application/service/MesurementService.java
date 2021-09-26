package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.CollectMesurement;
import codes.harsha.co2stat.application.port.out.MesurementCreatePort;
import codes.harsha.co2stat.application.port.out.MesurementQueryPort;
import codes.harsha.co2stat.application.port.out.SensorQueryPort;
import codes.harsha.co2stat.domain.Mesurement;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class MesurementService implements CollectMesurement {

    private final SensorQueryPort sensorQueryPort;

    private final MesurementCreatePort mesurementCreatePort;

    private final MesurementQueryPort mesurementQueryPort;

    public MesurementService(SensorQueryPort sensorQueryPort,
                             MesurementCreatePort mesurementCreatePort,
                             MesurementQueryPort mesurementQueryPort) {
        this.sensorQueryPort = sensorQueryPort;
        this.mesurementCreatePort = mesurementCreatePort;
        this.mesurementQueryPort = mesurementQueryPort;
    }

    @Override
    public void collect(String sensorId, double co2Level, String dateTime) {

        Assert.notNull(sensorId, "");
        Assert.notNull(dateTime, "");

        Sensor sensor = sensorQueryPort.find(sensorId);

        ZonedDateTime date = ZonedDateTime.parse(dateTime);
        Mesurement mesurement = new Mesurement(co2Level, date, sensor);
        mesurementCreatePort.save(mesurement);

        List<Mesurement> lastTwo = mesurementQueryPort.findLastMesurements(sensorId, 2);

    }
}
