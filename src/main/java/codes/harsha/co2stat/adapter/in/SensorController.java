package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.in.GetSensorStatus;
import codes.harsha.co2stat.application.port.in.ListAlerts;
import codes.harsha.co2stat.domain.Alert;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sensors")
public class SensorController {

    private final CreateSensor createSensor;

    private final GetSensorStatus getSensorStatus;

    private final ListAlerts listAlerts;

    public SensorController(CreateSensor createSensor, GetSensorStatus getSensorStatus, ListAlerts listAlerts) {
        this.getSensorStatus = getSensorStatus;
        this.createSensor = createSensor;
        this.listAlerts = listAlerts;
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@PathVariable String uuid){
        createSensor.create(uuid);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  SensorResponse getStatus(@PathVariable String uuid){
        SensorResponse sensorResponse = new SensorResponse();
        sensorResponse.status = getSensorStatus.getStatus(uuid);
        return sensorResponse;
    }

    class SensorResponse{
        public String status;
    }

    @RequestMapping(value = "/{uuid}/alerts", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public List<AlertsResponse> getAlerts(@PathVariable String uuid){

        List<Alert> alerts = listAlerts.getAlerts(uuid);
        List<AlertsResponse> alertsResponses = new ArrayList<>();

        for (Alert alert: alerts
             ) {
            AlertsResponse alertsResponse = new AlertsResponse();
            alertsResponse.startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX").format(alert.getStartTime());
            alertsResponse.endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX").format(alert.getEndTime());
            alertsResponse.mesurement1 = alert.getMesurements()[0];
            alertsResponse.mesurement2 = alert.getMesurements()[1];
            alertsResponse.mesurement3 = alert.getMesurements()[2];
            alertsResponses.add(alertsResponse);
        }

        return alertsResponses;
    }

    class AlertsResponse{
        public String startTime;
        public String endTime;
        public int mesurement1;
        public int mesurement2;
        public int mesurement3;
    }

}
