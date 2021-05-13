package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class BadConditionException extends RuntimeException {

    private String codeException;

    public BadConditionException(String message) {
        super(message);
    }

    public BadConditionException(String message, String codeException) {

        super(message);
        this.codeException = codeException;

    }
}
