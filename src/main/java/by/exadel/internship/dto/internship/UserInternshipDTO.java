package by.exadel.internship.dto.internship;


import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.location.LocationDTO;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInternshipDTO {

    private UUID id;

    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private Set<Skill> skills;

    @NotNull
    private InternshipFormat internshipFormat;

    @NotNull
    private List<LocationDTO> locationList;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Size(max = 250)
    @NotNull
    private LocalDate publicationDate;

    @Size(max = 250)
    private String techSkills;

    @Size(max = 150)
    private String description;

    @Size(max = 150)
    private String requirements;

    @NotNull
    private Integer capacity;

    @NotNull
    private LocalDate registrationStartDate;

    @NotNull
    private LocalDate registrationEndDate;

}
