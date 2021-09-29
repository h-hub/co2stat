package codes.harsha.co2stat.service;

import codes.harsha.co2stat.application.port.out.AlertQueryPort;
import codes.harsha.co2stat.application.service.AlertService;
import codes.harsha.co2stat.domain.Alert;
import codes.harsha.co2stat.domain.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlertServiceTest {

    @Mock
    AlertQueryPort alertQueryPort;

    @InjectMocks
    AlertService alertService;

    @Test
    public void when_getAlerts_it_should_return_list(){
        Sensor sensor = new Sensor("ABC");
        Alert alert = new Alert(sensor, ZonedDateTime.now(), ZonedDateTime.now(), new int[]{1,2,3});
        List<Alert> alerts = new ArrayList<>();
        alerts.add(alert);

        when(alertQueryPort.findAlerts(anyString())).thenReturn(alerts);

        assertEquals(alertService.getAlerts("ABC").get(0).getMesurements()[1], 2);

    }
}
