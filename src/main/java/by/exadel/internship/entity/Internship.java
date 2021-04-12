package by.exadel.internship.entity;
import by.exadel.internship.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "internship")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

//    private List<FormFullDTO> formList;
//    private List<UserDTO> techList;
//    private List<UserDTO> adminList;
//    private List<LocationDTO> countryList;
//    private InternshipFormat InternshipFormat;
//    private List<Technology> technologyList;

}
