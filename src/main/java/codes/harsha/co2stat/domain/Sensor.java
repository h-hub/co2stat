package codes.harsha.co2stat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

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

    public void setStatus(List<Mesurement> lastMesurements) {

        Assert.isTrue(lastMesurements.size()==3, "Invalid list size");

        boolean isWarn = lastMesurements.get(0).getCo2() > 2000;

        boolean isOver200 = lastMesurements.stream().allMatch( mesurement -> mesurement.getCo2() > 2000 );

        boolean isBelow200 = lastMesurements.stream().allMatch( mesurement -> mesurement.getCo2() < 2000 );

        if(!isBelow200 && !isOver200 && isWarn){
            this.status = Status.WARN;
        } else {
            if(isOver200){
                this.status = Status.ALERT;
            }
            if(isBelow200){
                this.status = Status.OK;
            }
        }
    }

    public enum Status {
        ALERT("ALERT"),
        WARN("WARN"),
        OK("OK");

        private String str;

        Status(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }

    public List<Mesurement> getMesurements() {
        return mesurements;
    }

    public void setMesurements(List<Mesurement> mesurements) {
        this.mesurements = mesurements;
    }

}

//{"_id":{"$oid":"615020333d318071c890dacd"},
//        "co2":2000,"dateTime":{"$date":"2019-02-01T18:55:47.000Z"},"sensor":{"$ref":"sensor","$id":"zxzxzxzxzxzxzxzx"},"_class":"codes.harsha.co2stat.domain.Mesurement"}


