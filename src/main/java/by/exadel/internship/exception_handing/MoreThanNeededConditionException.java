package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class MoreThanNeededConditionException extends RuntimeException {

    private String codeException;

    public MoreThanNeededConditionException(String message) {
        super(message);
    }

    public MoreThanNeededConditionException(String message, String codeException) {

        super(message);
        this.codeException = codeException;

    }
}
