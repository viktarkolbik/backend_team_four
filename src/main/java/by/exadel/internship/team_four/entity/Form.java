package by.exadel.internship.team_four.entity;


import by.exadel.internship.team_four.entity.enums.EnglishLevel;
import by.exadel.internship.team_four.entity.enums.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {

    private UUID id;
    private String name;
    private String lastName;
    private String middleName;
    private LocalDate birthdayDate;
    private String email;
    private String phoneNumb;
    private String messengers;
    private EnglishLevel englishLevel;
    private String country;
    private String city;
    private String experience;
    private String education;
    private String filePath;
    private Interview interview;
    private FormStatus formStatus;


}
