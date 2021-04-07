package by.exadel.internship.exception_handing;

public class NotFoundException extends RuntimeException{
    private String simpleClassName;
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String simpleClassName) {
        super(message);
        this.simpleClassName = simpleClassName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }
}
