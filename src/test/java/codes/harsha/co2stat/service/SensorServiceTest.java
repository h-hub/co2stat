package codes.harsha.co2stat.service;

import codes.harsha.co2stat.application.port.out.SensorCreatePort;
import codes.harsha.co2stat.application.port.out.SensorQueryPort;
import codes.harsha.co2stat.application.service.SensorService;
import codes.harsha.co2stat.domain.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SensorServiceTest {

    @Mock
    SensorCreatePort sensorCreatePort;

    @Mock
    SensorQueryPort sensorQueryPort;

    @InjectMocks
    SensorService sensorService;

    @Test(expected = IllegalArgumentException.class)
    public void when_create_withInvalidParameters_shouldThrowError(){
        sensorService.create(null);
    }

    @Test
    public void when_getStatus_shouldReturnString(){
        Sensor sensor = new Sensor("ABC");
        when(sensorQueryPort.find("ABC")).thenReturn(sensor);
        assertEquals(sensorService.getStatus("ABC"),"");
    }
}
