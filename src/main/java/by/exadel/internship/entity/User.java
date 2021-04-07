package by.exadel.internship.entity;
import by.exadel.internship.dto.enums.Technology;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.internshipDTO.UserInternshipDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="user",
//        discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @Column(name = "u_id")
    private UUID id;

    @Column(name = "u_lastname")
    private String lastName;

    @Column(name = "u_firstname")
    private String firstName;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_login")
    private String login;

    @Column(name = "u_password")
    private String password;

    @Column(name = "u_role_id")
    private UserRole userRole;

//    @Column(name = "intership_list")
//    private List<UserInternshipDTO> listOfInternships;
    //private List<LocalDateTime> freeInterviewDates;
}
