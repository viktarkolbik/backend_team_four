package by.exadel.internship.dto;


import by.exadel.internship.dto.enums.FormatOfInternship;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Internship {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate publicationDate;
    private String techSkills;
    private List<InternshipLocation> countryList;
    private FormatOfInternship formatOfInternship;
    private List<Technology> technologyList;
    private List<Form> formList;
    private List<User> techList;
    private List<User> adminList;
    private String description;
    private String requirements;
    private Integer capacity;
    private LocalDate startDateRegistration;
    private LocalDate endDateRegistration;

    public Internship(String name, LocalDate startDateInternship, LocalDate endDateInternship,
                      String techSkills, List<InternshipLocation> countryList, FormatOfInternship formatOfInternship,
                      List<Technology> technologyList, String description, String requirements, Integer capacity,
                      LocalDate startDateRegistration, LocalDate endDateRegistration) {
        this.name = name;
        this.startDate = startDateInternship;
        this.endDate = endDateInternship;
        this.techSkills = techSkills;
        this.countryList = countryList;
        this.formatOfInternship = formatOfInternship;
        this.technologyList = technologyList;
        this.description = description;
        this.requirements = requirements;
        this.capacity = capacity;
        this.startDateRegistration = startDateRegistration;
        this.endDateRegistration = endDateRegistration;
    }
}
