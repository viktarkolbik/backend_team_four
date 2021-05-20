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

    @Size(max = 150)
    private String techSkills;

    @Size(max = 500)
    private String description;

    @Size(max = 500)
    private String requirements;

    @NotNull
    private LocalDate publicationDate;

    @Size(max = 1000)
    @NotNull
    private Integer capacity;

    @NotNull
    private LocalDate registrationStartDate;

    @NotNull
    private LocalDate registrationEndDate;

}
