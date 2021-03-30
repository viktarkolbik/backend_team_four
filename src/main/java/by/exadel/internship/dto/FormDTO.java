package by.exadel.internship.dto;

import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {

    private UUID id;
    private String name;
    private String lastName;
    //not req
    private String middleName;
    private String email;
    private String phoneNumb;
    private String skype;
    private EnglishLevel englishLevel;
    private String country;
    private String city;
    //not req
    private String experience;
    //private String primarySkill; ask Jane
    private String education;
    private String filePath;
    private InterviewDTO interview;
    private FormStatus formStatus;
    //this field is questionable
    private TimeForCallDTO timeForCall;

}
