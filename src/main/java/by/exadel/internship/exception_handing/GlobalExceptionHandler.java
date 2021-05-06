package by.exadel.internship.exception_handing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Send an exception in json format
 * Use ResponseEntity for sending exception(we can use any class) like response on a server
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String ACCESS_ERROR_MESSAGE = "Forbidden";

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            NotFoundException exception) {
        IncorrectData data = incorrectDataFillingNotFoundException(exception);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            FileNotUploadException exception) {
        IncorrectData data = incorrectDataFilling(exception);
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            Exception exception) {
        IncorrectData data = incorrectDataFilling(exception);
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            BadCredentialsException exception) {
        IncorrectData data = incorrectDataFilling(exception);
        return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            AccessDeniedException exception) {
        IncorrectData data = incorrectDataFillingAccessException(exception);
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
    }

    private IncorrectData incorrectDataFilling(Exception exception) {
        IncorrectData incorrectData = new IncorrectData();
        log.error("Message: {} Error UUID code: {}", exception.getMessage(), incorrectData.getErrorCode());
        incorrectData.setMessage(exception.getMessage());
        return incorrectData;
    }

    private IncorrectData incorrectDataFillingNotFoundException(NotFoundException exception) {
        IncorrectData incorrectData = new IncorrectData();
        log.error("Message: {} Code exception: {} Error UUID code: {}",
                exception.getMessage(),
                exception.getCodeException(),
                incorrectData.getErrorCode());
        incorrectData.setMessage(exception.getMessage());
        incorrectData.setCodeException(exception.getCodeException());
        return incorrectData;
    }

    private IncorrectData incorrectDataFillingAccessException(AccessDeniedException exception) {
        IncorrectData incorrectData = new IncorrectData();
        log.error("Message: {} Error UUID code: {}", ACCESS_ERROR_MESSAGE, incorrectData.getErrorCode());
        incorrectData.setMessage(ACCESS_ERROR_MESSAGE);
        return incorrectData;
    }
}
