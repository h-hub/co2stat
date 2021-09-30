package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.in.GetSensorStatus;
import codes.harsha.co2stat.application.port.in.ListAlerts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

}
