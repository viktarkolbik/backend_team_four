package by.exadel.internship.dto.internship;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInternshipDTO extends BaseInternshipDTO {

    @Size(max = 250)
    private String techSkills;

    @Size(max = 150)
    private String description;

    @Size(max = 150)
    private String requirements;

    @Size(max = 250)
    @NotNull
    private LocalDate publicationDate;

    @NotNull
    private Integer capacity;

    @NotNull
    private LocalDate registrationStartDate;

    @NotNull
    private LocalDate registrationEndDate;

}
