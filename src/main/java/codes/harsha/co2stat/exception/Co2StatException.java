package codes.harsha.co2stat.exception;

import java.util.HashMap;
import java.util.Map;

public class Co2StatException extends RuntimeException{

    public Co2StatException(String message) {
        super(message);
    }

    public static Map getErrorMessageBody(String message){
        Map<String, String> body = new HashMap<>();
        body.put("message",message);
        return body;
    }
}
