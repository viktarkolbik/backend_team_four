package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDTO {

    private String firstName;
    private String lastName;
    //not req
    private String middleName;
    private String email;
    private String phoneNumber;
    private String skype;
    private EnglishLevel englishLevel;
    private String country;
    private String city;
    //not req
    private String primarySkill;
    private String experience;
    private String education;
    private String filePath;

}
