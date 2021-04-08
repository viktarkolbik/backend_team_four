package by.exadel.internship.exception_handing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectData {
    private String message;
    private String codeException;
    //To match the log
    private UUID errorCode;

}
