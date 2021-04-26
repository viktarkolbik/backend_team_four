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
@ToString(exclude = {"users"})
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

    @Column(name = "inship_deleted")
    private boolean deleted;

    @Column(name = "inship_format_name")
    @Enumerated(EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private InternshipFormat internshipFormat;

    @Column(name = "is_name", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "internship_skill", joinColumns = @JoinColumn(name = "is_inship_id"))
    private List<Skill> skills;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_internship",
            joinColumns = @JoinColumn(name = "ui_inship_id"),
            inverseJoinColumns = @JoinColumn(name = "ui_u_id"))
    private List<User> users;

//    private List<Location> countryList;
}
