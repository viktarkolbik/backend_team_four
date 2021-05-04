package by.exadel.internship.dto.form;

import by.exadel.internship.dto.time_for_call.TimeForCallDTO;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDTO {

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String middleName;

    @NotNull
    @Email
    @Size(max = 254)
    private String email;

    @NotNull
    @Size(max = 15)
    private String phoneNumber;

    @NotNull
    @Size(max = 254)
    private String skype;

    @NotNull
    @Size(max = 25)
    private EnglishLevel englishLevel;

    @Size(max = 25)
    private CountryDTO country;

    @Size(max = 50)
    private CityDTO city;

    @Size(max = 50)
    private String primarySkill;

    @Size(max = 500)
    private String experience;

    @Size(max = 500)
    private String education;

    @Size(max = 232)
    private String filePath;

    private List<TimeForCallDTO> timeForCallList;

    private UUID internshipId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean sendEmail = false;

}