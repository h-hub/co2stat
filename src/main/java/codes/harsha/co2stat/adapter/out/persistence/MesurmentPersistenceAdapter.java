package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.application.port.out.MesurementCreatePort;
import codes.harsha.co2stat.application.port.out.MesurementQueryPort;
import codes.harsha.co2stat.domain.Mesurement;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Component
public class MesurmentPersistenceAdapter implements MesurementCreatePort, MesurementQueryPort {

    private final MesurementRepositoryMongo mesurementRepositoryMongo;

    private final MongoTemplate mongoTemplate;

    public MesurmentPersistenceAdapter(MesurementRepositoryMongo mesurementRepositoryMongo, MongoTemplate mongoTemplate) {
        this.mesurementRepositoryMongo = mesurementRepositoryMongo;
        this.mongoTemplate = mongoTemplate;
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
    
    @Override
    public double getAverage(ZonedDateTime from, ZonedDateTime to, String sensorId){

        List<AggregationOperation> aggregationOperations = extracted(from, to, sensorId);

        GroupOperation groupOperation = Aggregation.group("_id=null").avg("co2").as("avg");
        aggregationOperations.add(groupOperation);

        Aggregation aggregation
                = newAggregation(aggregationOperations);

        AggregationResults<AverageData> output
                = mongoTemplate.aggregate(aggregation, "mesurement", AverageData.class);
        
        return output.getMappedResults().get(0).avg;

    }

    private List<AggregationOperation> extracted(ZonedDateTime from, ZonedDateTime to, String sensorId) {

        ObjectOperators.ObjectToArray.valueOfToArray("sensor");

        var sensorAsArray = ObjectOperators.valueOf("sensor")
                .toArray();

        var sensorArrayElementAtOne = ArrayOperators.ArrayElemAt.arrayOf(sensorAsArray).elementAt(1);

        AddFieldsOperation addFieldsOperationSensorObject = Aggregation.addFields().addFieldWithValue("sensor", sensorArrayElementAtOne).build();

        AddFieldsOperation addFieldsOperationSensorId = Aggregation.addFields().addFieldWithValue("sensor", "$sensor.v").build();

        LookupOperation lookupOperationSensor = Aggregation.lookup("sensor", "sensor", "_id", "sensor");

        var sensorArrayElementAtZero = ArrayOperators.ArrayElemAt.arrayOf("sensor").elementAt(0);

        AddFieldsOperation addFieldsOperationSensorIdModified = Aggregation.addFields().addFieldWithValue("sensor", sensorArrayElementAtZero).build();

        Date fromDate = Date.from(from.toInstant());
        Date toDate = Date.from(to.toInstant());
        Criteria dateCriteria =
                Criteria.where("dateTime").gte(fromDate).lte(toDate);
        Criteria sensorCriteria = Criteria.where("sensor._id").is(sensorId);
        Criteria dateAndSensorCriteria = new Criteria().andOperator(dateCriteria,sensorCriteria);

        MatchOperation matchOperation = Aggregation.match(dateAndSensorCriteria);

        List<AggregationOperation> aggregationOperations = new ArrayList<>();
        aggregationOperations.add(addFieldsOperationSensorObject);
        aggregationOperations.add(addFieldsOperationSensorId);
        aggregationOperations.add(lookupOperationSensor);
        aggregationOperations.add(addFieldsOperationSensorIdModified);
        aggregationOperations.add(matchOperation);

        return aggregationOperations;
    }

}
