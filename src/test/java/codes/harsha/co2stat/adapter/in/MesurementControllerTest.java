package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CollectMesurement;
import codes.harsha.co2stat.application.port.in.CollectMetrics;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MesurementController.class)
public class MesurementControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CollectMesurement collectMesurement;

    @MockBean
    CollectMetrics collectMetrics;

    @Test
    public void when_getMetrics_shouldReturnJson() throws Exception {

        MesurementController.Metrics metrics = new MesurementController.Metrics();
        metrics.avgLast30Days = 0;
        metrics.maxLast30Days = 0;

        MvcResult mvcResult = mvc.perform(
                        get("/api/v1/sensors/ABC/metrics")).
                andExpect(status().isOk()).
                andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResponseBody = mapper.writeValueAsString(metrics);

        assertEquals(actualResponseBody, expectedResponseBody);

    }


}
