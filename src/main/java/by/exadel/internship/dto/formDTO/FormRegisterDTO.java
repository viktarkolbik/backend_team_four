package by.exadel.internship.dto.formDTO;

import by.exadel.internship.dto.enums.EnglishLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.*;

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
    private String country;

    @NotNull
    @Size(max=50)
    private String city;

    @Size(max=50)
    private String primarySkill;

    @Size(max=500)
    private String experience;

    @Size(max=500)
    private String education;

    @Size(max=232)
    private String filePath;

}