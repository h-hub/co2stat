package codes.harsha.co2stat.adapter.out.persistence;

import codes.harsha.co2stat.domain.Mesurement;
import codes.harsha.co2stat.domain.Sensor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface MesurementRepositoryMongo extends MongoRepository<Mesurement, String>  {

    List<Mesurement> findAllBySensorId(String sensorId, Pageable pageable);

    @Aggregation(pipeline = {"    {\n" +
            "    $addFields: {\n" +
            "      sensor: {\n" +
            "        $arrayElemAt: [{ $objectToArray: $sensor }, 1]\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    $addFields: {\n" +
            "      sensor: $sensor.v\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    $lookup: {\n" +
            "      from: sensor,\n" +
            "      localField: sensor,\n" +
            "      foreignField: _id,\n" +
            "      as: sensor\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    $addFields: {\n" +
            "      sensor: { $arrayElemAt: [$sensor, 0] }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    $match : {\n" +
            "      $and:[\n" +
            "        {dateTime: { $gte: ISODate('2019-02-01T10:03:46Z'), $lte: ISODate('2019-02-03T10:03:46Z') }},\n" +
            "        {sensor._id: 'zxzxzxzxzxzxzxzx' }\n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    $group : {\n" +
            "      _id: null,\n" +
            "      avg: {\n" +
            "        $avg: $co2\n" +
            "      }\n" +
            "    }\n" +
            "  }"})
    AggregationResults<String> getAverage(String from, String to, String sensorId);
}
