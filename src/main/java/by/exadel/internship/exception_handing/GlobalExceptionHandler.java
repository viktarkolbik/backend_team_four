package by.exadel.internship.exception_handing;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

/*
* Send an exception in json format
* Use ResponseEntity for sending exception(we can use any class) like response on a server
*/

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            NotFoundException exception){
        UUID uuidErrorCode = UUID.randomUUID();
        log.error("Message: " + exception.getMessage()+ " Code exception: " + exception.getCodeException() +
                " Error UUID code: " + uuidErrorCode);
        IncorrectData data =  new IncorrectData();
        data.setMessage(exception.getMessage());
        data.setCodeException(exception.getCodeException());
        data.setErrorCode(uuidErrorCode);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            Exception exception){
        UUID uuidErrorCode = UUID.randomUUID();
        System.out.println(exception.getClass());
        log.error("Message: " + exception.getMessage() + " Error UUID code: " + uuidErrorCode);
        IncorrectData data =  new IncorrectData();
        data.setMessage(exception.getMessage());
        data.setErrorCode(uuidErrorCode);
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            BadCredentialsException exception){
        UUID uuidErrorCode = UUID.randomUUID();
        System.out.println(exception.getClass());
        log.error("Message: " + exception.getMessage() + " Error UUID code: " + uuidErrorCode);
        IncorrectData data =  new IncorrectData();
        data.setMessage(exception.getMessage());
        data.setErrorCode(uuidErrorCode);
        return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
    }
}
