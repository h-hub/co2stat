package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import codes.harsha.co2stat.application.port.in.GetSensorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorController {

    private final CreateSensor createSensor;

    private final GetSensorStatus getSensorStatus;

    public SensorController(CreateSensor createSensor, GetSensorStatus getSensorStatus) {
        this.getSensorStatus = getSensorStatus;
        this.createSensor = createSensor;
    }

    @RequestMapping(value = "/sensor/{uuid}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@PathVariable String uuid){
        createSensor.create(uuid);
    }

    @RequestMapping(value = "/api/v1/sensors/{uuid}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  SensorResponse getStatus(@PathVariable String uuid){
        SensorResponse sensorResponse = new SensorResponse();
        sensorResponse.status = getSensorStatus.getStatus(uuid);
        return sensorResponse;
    }

    class SensorResponse{
        public String status;
    }

}
