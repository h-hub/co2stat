package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.MesurementCreatePort;
import codes.harsha.co2stat.domain.Mesurement;
import org.springframework.stereotype.Component;

@Component
public class MesurmentPersistenceAdapter implements MesurementCreatePort {

    private final MesurementRepositoryMongo mesurementRepositoryMongo;

    public MesurmentPersistenceAdapter(MesurementRepositoryMongo mesurementRepositoryMongo) {
        this.mesurementRepositoryMongo = mesurementRepositoryMongo;
    }

    @Override
    public void save(Mesurement mesurement) {
        mesurementRepositoryMongo.save(mesurement);
    }
}
