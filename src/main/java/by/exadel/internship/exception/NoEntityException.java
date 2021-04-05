package by.exadel.internship.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoEntityException extends RuntimeException {

    public NoEntityException() {
        super();
    }

    public NoEntityException(String message) {
        super(message);
    }

    public NoEntityException(UUID uuid) {

    }
}
