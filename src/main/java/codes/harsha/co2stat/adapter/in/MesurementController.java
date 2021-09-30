package codes.harsha.co2stat.adapter.in;

import codes.harsha.co2stat.application.port.in.CollectMesurement;
import codes.harsha.co2stat.application.port.in.CollectMetrics;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sensors")
@Validated
public class MesurementController {

    private final CollectMesurement collectMesurement;

    private final CollectMetrics collectMetrics;

    public MesurementController(CollectMesurement collectMesurement, CollectMetrics collectMetrics) {
        this.collectMetrics = collectMetrics;
        this.collectMesurement = collectMesurement;
    }

    @RequestMapping(value = "/{uuid}/mesurements", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  void create(@RequestBody @Valid MesurementPayload mesurementPayload, @PathVariable String uuid){

        collectMesurement.collect(uuid, mesurementPayload.co2, mesurementPayload.time);
    }

    @RequestMapping(value = "/{uuid}/metrics", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public  Metrics getMetrics(@PathVariable String uuid){

        int avgLast30Days = collectMetrics.getAvgLast30Days(uuid);
        int maxLast30Days = collectMetrics.getMaxLast30Days(uuid);

        Metrics metrics = new Metrics();
        metrics.avgLast30Days = avgLast30Days;
        metrics.maxLast30Days = maxLast30Days;

        return metrics;
    }

    static class Metrics{
        public int maxLast30Days;
        public int avgLast30Days;
    }

}
