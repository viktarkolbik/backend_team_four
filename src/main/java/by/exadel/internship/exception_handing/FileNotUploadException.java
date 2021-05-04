package by.exadel.internship.exception_handing;

import lombok.Data;

@Data
public class FileNotUploadException extends RuntimeException{
    public FileNotUploadException(String message) {
        super(message);
    }
}
