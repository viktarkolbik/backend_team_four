package by.exadel.internship.entity;

import by.exadel.internship.auditing.Auditable;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "internship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "internshipFormat")
public class Internship extends Auditable<String> {

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

    @Column(name = "inship_format_name")
    @Enumerated(EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private InternshipFormat internshipFormat;

    @Column(name = "is_name", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "internship_skill", joinColumns = @JoinColumn(name = "is_inship_id"))
    private List<Skill> skills;

//    private List<Form> formList;
//    private List<User> techList;
//    private List<User> adminList;
//    private List<Location> countryList;

}
