package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.MesurementCreatePort;
import codes.harsha.co2stat.application.port.out.MesurementQueryPort;
import codes.harsha.co2stat.domain.Mesurement;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MesurmentPersistenceAdapter implements MesurementCreatePort, MesurementQueryPort {

    private final MesurementRepositoryMongo mesurementRepositoryMongo;

    public MesurmentPersistenceAdapter(MesurementRepositoryMongo mesurementRepositoryMongo) {
        this.mesurementRepositoryMongo = mesurementRepositoryMongo;
    }

    @Override
    public void save(Mesurement mesurement) {
        mesurementRepositoryMongo.save(mesurement);
    }

    @Override
    public List<Mesurement> findLastMesurements(String sensorId, int mesurementCount) {
        Pageable topX = PageRequest.of(0, mesurementCount, Sort.Direction.DESC, "dateTime");


        return mesurementRepositoryMongo.findAllBySensorId(sensorId, topX);
    }
}
