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

    @NotNull
    @Size(max = 150)
    private String techSkills;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 500)
    private String requirements;

    @NotNull
    private LocalDate publicationDate;

    @NotNull
    @Size(max = 1000)
    private Integer capacity;

    @NotNull
    private LocalDate registrationStartDate;

    @NotNull
    private LocalDate registrationEndDate;

}
