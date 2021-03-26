package by.exadel.internship.team_four.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Internship {

    private long id;
    private String name;
    private Date startDateInternship;
    private Date endDateInternship;
    private String techSkills;
    //private List<Country> countryList;
    //private FormatOfInternship formatOfInternship;
    //private List<Technology> technologyList;
    //private List<Form> formList;
    //private List<User> technicList;
    //private List<User> adminList;
    private String description;
    private String requirements;
    private int capacity;
    private Date startDateRegistration;
    private Date endDateRegistration;

}
