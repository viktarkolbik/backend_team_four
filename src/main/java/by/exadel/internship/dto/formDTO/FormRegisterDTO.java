package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.*;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDTO {

    @NotNull
    @Size(max=50)
    private String firstName;

    @NotNull
    @Size(max=50)
    private String lastName;

    @Size(max=50)
    private String middleName;

    @NotNull
    @Email
    @Size(max=254)
    private String email;

    @NotNull
    @Size(max=15)
    private String phoneNumber;

    @NotNull
    @Size(max=254)
    private String skype;

    @NotNull
    @Size(max=25)
    private EnglishLevel englishLevel;

    @NotNull
    @Size(max=25)
    private Country country;

    @NotNull
    @Size(max=50)
    private City city;

    @Size(max=50)
    private String primarySkill;

    @Size(max=500)
    private String experience;

    @Size(max=500)
    private String education;

    @Size(max=232)
    private String filePath;

    private List<TimeForCallDTO> timeForCallList;

    private String InternshipId;

}