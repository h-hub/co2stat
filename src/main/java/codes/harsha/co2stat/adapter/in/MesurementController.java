package codes.harsha.co2stat.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sensors")
public class MesurementController {

    @RequestMapping(value = "/{uuid}/mesurements", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@RequestBody @Valid MesurementPayload mesurementPayload, @PathVariable String uuid){

        System.out.println();
    }

}
