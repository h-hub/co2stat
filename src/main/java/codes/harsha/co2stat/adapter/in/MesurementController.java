package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CollectMesurement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sensors")
public class MesurementController {

    private final CollectMesurement collectMesurement;

    public MesurementController(CollectMesurement collectMesurement) {
        this.collectMesurement = collectMesurement;
    }

    @RequestMapping(value = "/{uuid}/mesurements", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@RequestBody @Valid MesurementPayload mesurementPayload, @PathVariable String uuid){

        collectMesurement.collect(uuid, mesurementPayload.co2, mesurementPayload.time);
    }

}
