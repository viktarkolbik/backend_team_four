package by.exadel.internship.dto.internship;

import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.location.LocationDTO;
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
public class BaseInternshipDTO {

    private UUID id;

    @Size(min=2, max=100)
    private String name;

    @NotNull
    private Set<Skill> skills;

    @NotNull
    private InternshipFormat internshipFormat;

    @NotNull
    private List<LocationDTO> locations;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
