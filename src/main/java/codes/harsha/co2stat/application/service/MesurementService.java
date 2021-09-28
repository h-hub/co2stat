package codes.harsha.co2stat.application.service;

import codes.harsha.co2stat.application.port.in.CollectMesurement;
import codes.harsha.co2stat.application.port.in.CollectMetrics;
import codes.harsha.co2stat.application.port.out.MesurementCreatePort;
import codes.harsha.co2stat.application.port.out.MesurementQueryPort;
import codes.harsha.co2stat.application.port.out.SensorCreatePort;
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
public class MesurementService implements CollectMesurement, CollectMetrics {

    private final SensorQueryPort sensorQueryPort;

    private final MesurementCreatePort mesurementCreatePort;

    private final MesurementQueryPort mesurementQueryPort;

    private final SensorCreatePort sensorCreatePort;

    public MesurementService(SensorQueryPort sensorQueryPort,
                             MesurementCreatePort mesurementCreatePort,
                             MesurementQueryPort mesurementQueryPort,
                             SensorCreatePort sensorCreatePort) {
        this.sensorQueryPort = sensorQueryPort;
        this.mesurementCreatePort = mesurementCreatePort;
        this.mesurementQueryPort = mesurementQueryPort;
        this.sensorCreatePort = sensorCreatePort;
    }

    @Override
    public void collect(String sensorId, int co2Level, String dateTime) {

        Assert.notNull(sensorId, "");
        Assert.notNull(dateTime, "");

        Sensor sensor = sensorQueryPort.find(sensorId);

        ZonedDateTime date = ZonedDateTime.parse(dateTime);
        Mesurement mesurement = new Mesurement(co2Level, date, sensor);
        mesurementCreatePort.save(mesurement);

        List<Mesurement> lastThree = mesurementQueryPort.findLastMesurements(sensorId, 3);

        sensor.setStatus(lastThree);
        sensorCreatePort.save(sensor);

    }

    @Override
    public double getMaxLast30Days(String sensorId) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime thirtyDaysAgo = today.minusDays(30);
        double max = mesurementQueryPort.getMax(thirtyDaysAgo, today, sensorId);
        return max;
    }

    @Override
    public double getAvgLast30Days(String sensorId) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime thirtyDaysAgo = today.minusDays(30);
        double average = mesurementQueryPort.getAverage(thirtyDaysAgo, today, sensorId);
        return average;
    }
}
