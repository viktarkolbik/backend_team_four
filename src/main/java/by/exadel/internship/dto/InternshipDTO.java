package by.exadel.internship.dto;


import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDTO {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate publicationDate;
    private String techSkills;
    private List<LocationDTO> countryList;
    private InternshipFormat InternshipFormat;
    private List<Technology> technologyList;
    private List<FormDTO> formList;
    private List<UserDTO> techList;
    private List<UserDTO> adminList;
    private String description;
    private String requirements;
    private Integer capacity;
    private LocalDate registrationStartDate;
    private LocalDate registrationEndDate;
}
