package codes.harsha.co2stat.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
public class Mesurement {

    private double co2;

    private ZonedDateTime dateTime;

    public Mesurement(double co2, ZonedDateTime dateTime) {
        this.co2 = co2;
        this.dateTime = dateTime;
    }

    public double getCo2() {
        return co2;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
