package codes.harsha.co2stat.service;

import codes.harsha.co2stat.application.port.out.MesurementQueryPort;
import codes.harsha.co2stat.application.service.MesurementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MesurementServiceTest {

    @Mock
    MesurementQueryPort mesurementQueryPort;

    @InjectMocks
    MesurementService mesurementService;


    @Test(expected = IllegalArgumentException.class)
    public void when_collect_withInvalidParameters_shouldThrowError(){
        mesurementService.collect(null, 4, "2021-09-01T19:11:02+00:00");
    }

    @Test
    public void when_getMaxLast30Days_shouldReturnDouble(){
        assertEquals(mesurementService.getMaxLast30Days("ABC"), 0);
    }

    @Test
    public void when_getAvgLast30Days_shouldReturnDouble(){
        assertEquals(mesurementService.getAvgLast30Days("ABC"), 0);
    }

}
