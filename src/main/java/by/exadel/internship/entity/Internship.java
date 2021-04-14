package by.exadel.internship.entity;

import by.exadel.internship.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Skill;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "internship")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE internship SET inship_deleted=true WHERE inship_id=?")
@Where(clause = "inship_deleted = false")
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

    @Column(name = "inship_deleted")
    private Boolean deleted;

    @Column(name = "inship_format_name")
    @Enumerated(EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private InternshipFormat internshipFormat;

    @Column(name = "is_name", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "internship_skill", joinColumns = @JoinColumn(name = "is_inship_id"))
    private Set<Skill> skills;

//    private List<Form> formList;
//    private List<User> techList;
//    private List<User> adminList;
//    private List<Location> countryList;
}
