package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class NotFoundConditionException extends RuntimeException {

    private String codeException;

    public NotFoundConditionException(String message) {
        super(message);
    }

    public NotFoundConditionException(String message, String codeException) {

        super(message);
        this.codeException = codeException;

    }
}
