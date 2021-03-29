package by.exadel.internship.dto;


import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {

    private UUID id;
    private String name;
    private String lastName;

    //не обяз
    private String middleName;
    private String email;
    private String phoneNumb;
    private String skype;
    private EnglishLevel englishLevel;
    private String country;
    private String city;

    //не обяз
    private String experience;

    //private String primarySkill; спросить у Жанны

    private String education;
    private String filePath;
    private Interview interview;
    private FormStatus formStatus;
    private TimeForCall timeForCall;



}
