package codes.harsha.co2stat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Sensor {

    @Id
    private String id;

    private Status status;

    @DBRef
    private List<Mesurement> mesurements;

    public Sensor(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    enum Status {
        ALERT,
        WARN,
        OK
    }

    public List<Mesurement> getMesurements() {
        return mesurements;
    }

    public void setMesurements(List<Mesurement> mesurements) {
        this.mesurements = mesurements;
    }
}


