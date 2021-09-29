package codes.harsha.co2stat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class Co2StatExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Co2StatException.class})
    protected ResponseEntity<Object> handleCo2StatException(RuntimeException ex) {
        Co2StatException co2StatException = (Co2StatException)ex;

        Map<String, String> body = Co2StatException.getErrorMessageBody(co2StatException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
