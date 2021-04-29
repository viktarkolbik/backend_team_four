package by.exadel.internship.dto.internship;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GuestInternshipDTO extends ShortInternshipDTO {

    @NotNull
    private LocalDate publicationDate;

    @Size(max=250)
    private String techSkills;

    @Size(max=150)
    private String description;

    @Size(max=150)
    private String requirements;

    @NotNull
    private Integer capacity;

    @NotNull
    private LocalDate registrationStartDate;

    @NotNull
    private LocalDate registrationEndDate;
}
