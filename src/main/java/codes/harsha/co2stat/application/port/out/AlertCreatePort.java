package codes.harsha.co2stat.application.port.out;

import codes.harsha.co2stat.domain.Alert;

public interface AlertCreatePort {
    void save(Alert alert);
}
