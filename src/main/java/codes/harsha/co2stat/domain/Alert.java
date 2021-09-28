package codes.harsha.co2stat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
public class Alert {

    @Id
    private String id;

    @DBRef
    private Sensor sensor;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private int[] mesurements;

    public Alert(Sensor sensor, ZonedDateTime startTime, ZonedDateTime endTime, int[] mesurements) {
        this.sensor = sensor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mesurements = mesurements;
    }
}
