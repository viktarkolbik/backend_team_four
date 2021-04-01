package by.exadel.internship.dto.internshipDTO;


import by.exadel.internship.dto.LocationDTO;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GuestInternshipDTO {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate publicationDate;
    private String techSkills;
    private List<LocationDTO> countryList;
    private InternshipFormat InternshipFormat;
    private List<Technology> technologyList;
    private String description;
    private String requirements;
    private Integer capacity;
    private LocalDate registrationStartDate;
    private LocalDate registrationEndDate;
}
