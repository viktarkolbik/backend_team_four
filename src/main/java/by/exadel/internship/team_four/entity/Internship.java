package by.exadel.internship.team_four.entity;

import by.exadel.internship.team_four.entity.enums.FormatOfInternship;
import by.exadel.internship.team_four.entity.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Internship {

    private long id;
    private String name;
    private LocalDate startDateInternship;
    private LocalDate endDateInternship;
    private String techSkills;
    private List<InternshipLocation> countryList;
    private FormatOfInternship formatOfInternship;
    private List<Technology> technologyList;
    private List<Form> formList;
    private List<User> techList;
    private List<User> adminList;
    private String description;
    private String requirements;
    private int capacity;
    private LocalDate startDateRegistration;
    private LocalDate endDateRegistration;

}
