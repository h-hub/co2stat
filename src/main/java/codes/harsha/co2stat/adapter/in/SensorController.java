package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CreateSensor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorController {

    private final CreateSensor createSensor;

    public SensorController(CreateSensor createSensor) {
        this.createSensor = createSensor;
    }

    @RequestMapping(value = "/sensor/{uuid}", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@PathVariable String uuid){
        createSensor.create(uuid);
    }

}
