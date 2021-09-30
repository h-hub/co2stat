package codes.harsha.co2stat.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SensorTest {

    @Test
    public void when_setStatus_whenCo2MoreThan2000ThreeTimes_shouldSetStatusAlert(){

        Sensor sensor = new Sensor("ABC");

        Mesurement mesurement1 = new Mesurement(2100, ZonedDateTime.now(), sensor);
        Mesurement mesurement2 = new Mesurement(2100, ZonedDateTime.now(), sensor);
        Mesurement mesurement3 = new Mesurement(2100, ZonedDateTime.now(), sensor);

        List<Mesurement> lastMesurements = List.of(mesurement1, mesurement2, mesurement3);

        sensor.setStatus(lastMesurements);

        assertEquals(sensor.getStatus(), Sensor.Status.ALERT);

    }

    @Test
    public void when_setStatus_whenCo2LessThan2000ThreeTimes_shouldSetStatusAlert(){

        Sensor sensor = new Sensor("ABC");

        Mesurement mesurement1 = new Mesurement(1100, ZonedDateTime.now(), sensor);
        Mesurement mesurement2 = new Mesurement(1100, ZonedDateTime.now(), sensor);
        Mesurement mesurement3 = new Mesurement(1100, ZonedDateTime.now(), sensor);

        List<Mesurement> lastMesurements = List.of(mesurement1, mesurement2, mesurement3);

        sensor.setStatus(lastMesurements);

        assertEquals(sensor.getStatus(), Sensor.Status.OK);

    }

    @Test
    public void when_setStatus_whenCo2MoreThan2000OneTimes_shouldSetStatusWarn(){

        Sensor sensor = new Sensor("ABC");

        Mesurement mesurement1 = new Mesurement(2100, ZonedDateTime.now(), sensor);
        Mesurement mesurement2 = new Mesurement(1100, ZonedDateTime.now(), sensor);
        Mesurement mesurement3 = new Mesurement(1100, ZonedDateTime.now(), sensor);

        List<Mesurement> lastMesurements = List.of(mesurement1, mesurement2, mesurement3);

        sensor.setStatus(lastMesurements);

        assertEquals(sensor.getStatus(), Sensor.Status.WARN);

    }
}
