package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.in.GetSensorStatus;
import codes.harsha.co2stat.application.port.in.ListAlerts;
import codes.harsha.co2stat.domain.Alert;
import codes.harsha.co2stat.domain.Sensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CreateSensor createSensor;

    @MockBean
    GetSensorStatus getSensorStatus;

    @MockBean
    ListAlerts listAlerts;

    @Test
    public void when_getStatus_shouldReturnJson() throws Exception {
        SensorController.SensorResponse sensorResponse = new SensorController.SensorResponse();
        sensorResponse.status = "OK";

        when(getSensorStatus.getStatus("ABC"))
                .thenReturn("OK");

        MvcResult mvcResult = mvc.perform(
                        get("/api/v1/sensors/ABC")).
                andExpect(status().isOk()).
                andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResponseBody = mapper.writeValueAsString(sensorResponse);

        assertEquals(actualResponseBody, expectedResponseBody);

    }

    @Test
    public void when_getAlerts_shouldReturnJson() throws Exception {
        Sensor sensor = new Sensor("ABC");
        String date = "2021-10-01T01:40:32+08:00";
        Alert alert = new Alert(sensor, ZonedDateTime.parse(date), ZonedDateTime.parse(date), new int[]{1,2,3});
        List<Alert> alerts = new ArrayList<>();
        alerts.add(alert);

        when(listAlerts.getAlerts("ABC"))
                .thenReturn(alerts);

        MvcResult mvcResult = mvc.perform(
                        get("/api/v1/sensors/ABC/alerts")).
                andExpect(status().isOk()).
                andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = "[{\"startTime\":\"2021-10-01T01:40:32+08:00\",\"endTime\":\"2021-10-01T01:40:32+08:00\",\"mesurement1\":1," +
                "\"mesurement2\":2,\"mesurement3\":3}]";
        assertEquals(actualResponseBody, expectedResponseBody);
    }

}
