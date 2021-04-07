package by.exadel.internship.exception_handing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* Send an exception in json format
* Use ResponseEntity for sending exception(we can use any class) like response on a server
*/

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger("by.exadel.service");

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            NotFoundException exception){
        logger.error(exception.getMessage(), exception.getSimpleClassName());
        IncorrectData data =  new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            Exception exception){
        logger.error(exception.getMessage());
        IncorrectData data =  new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
