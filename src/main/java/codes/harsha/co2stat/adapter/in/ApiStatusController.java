package codes.harsha.co2stat.adapter.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiStatusController {

    @RequestMapping("/status")
    public String healthCheck() {
        return "API is OK";
    }

}
