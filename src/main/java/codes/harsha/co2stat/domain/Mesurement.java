package codes.harsha.co2stat.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
public class Mesurement {

    private double co2;

    private ZonedDateTime dateTime;

    @DBRef
    private Sensor sensor;

    public Mesurement(double co2, ZonedDateTime dateTime, Sensor sensor) {
        this.co2 = co2;
        this.dateTime = dateTime;
        this.sensor = sensor;
    }

    public double getCo2() {
        return co2;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
