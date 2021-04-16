package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private String codeException;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String codeException) {

        super(message);
        this.codeException = codeException;

    }
}
