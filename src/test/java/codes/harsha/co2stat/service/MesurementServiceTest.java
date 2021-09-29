package codes.harsha.co2stat.service;

import codes.harsha.co2stat.application.port.out.*;
import codes.harsha.co2stat.application.service.MesurementService;
import codes.harsha.co2stat.domain.Sensor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MesurementServiceTest {

    @Mock
    SensorQueryPort sensorQueryPort;

    @Mock
    MesurementCreatePort mesurementCreatePort;

    @Mock
    MesurementQueryPort mesurementQueryPort;

    @Mock
    SensorCreatePort sensorCreatePort;

    @Mock
    AlertCreatePort alertCreatePort;

    @InjectMocks
    MesurementService mesurementService;


    @Test(expected = IllegalArgumentException.class)
    public void when_collect_withInvalidParameters_then_shouldThrowError(){

        mesurementService.collect(null, 4, "2021-09-01T19:11:02+00:00");

    }

}
