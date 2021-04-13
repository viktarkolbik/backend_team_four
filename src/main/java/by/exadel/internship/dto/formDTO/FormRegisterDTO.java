package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDTO {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String skype;
    private EnglishLevel englishLevel;
    private String country;
    private String city;
    private String primarySkill;
    private String experience;
    private String education;
    private List<TimeForCallDTO> timeForCallList;
    private String filePath;

}