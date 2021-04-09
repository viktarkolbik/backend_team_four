package by.exadel.internship.entity;


import by.exadel.internship.dto.enums.InternshipFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "internship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Internship {

    @Id
    @Column(name = "inship_id")
    private UUID id;

    @Column(name = "inship_name")
    private String name;

    @Column(name = "inship_start_date")
    private LocalDate startDate;

    @Column(name = "inship_end_date")
    private LocalDate endDate;

    @Column(name = "inship_publication_date")
    private LocalDate publicationDate;

    @Column(name = "inship_tech_skills")
    private String techSkills;

    @Column(name = "inship_description")
    private String description;

    @Column(name = "inship_requirements")
    private String requirements;

    @Column(name = "inship_capacity")
    private Integer capacity;

    @Column(name = "inship_registration_start_date")
    private LocalDate registrationStartDate;

    @Column(name = "inship_registration_end_date")
    private LocalDate registrationEndDate;


    @Column(name = "inship_format_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private InternshipFormat InternshipFormat;

    @ManyToOne
    @JoinColumn(name="inship_technology_name", nullable=false)
    private Technology technology;

//    private List<Form> formList;
//    private List<User> techList;
//    private List<User> adminList;
//    private List<Location> countryList;

}
