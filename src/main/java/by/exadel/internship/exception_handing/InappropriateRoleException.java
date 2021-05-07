package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class InappropriateRoleException extends RuntimeException {
    private String codeException;

    public InappropriateRoleException(String message) {
        super(message);
    }

    public InappropriateRoleException(String message, String codeException) {
        super(message);
        this.codeException = codeException;

    }
}
