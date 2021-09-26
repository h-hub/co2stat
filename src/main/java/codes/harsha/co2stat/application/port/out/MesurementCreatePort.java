package codes.harsha.co2stat.application.port.out;

import codes.harsha.co2stat.domain.Mesurement;

public interface MesurementCreatePort {
    void save(Mesurement mesurement);
}
