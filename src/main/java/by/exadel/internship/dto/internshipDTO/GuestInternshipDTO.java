package by.exadel.internship.dto.internshipDTO;


import by.exadel.internship.dto.LocationDTO;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GuestInternshipDTO {

    private UUID id;

    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private LocalDate publicationDate;

    @Size(max = 250)
    private String techSkills;

    @NotNull
    private List<LocationDTO> countryList;

    @NotNull
    private InternshipFormat internshipFormat;

    @NotNull
    private Set<Skill> skills;

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
