package by.exadel.internship.entity.user;
import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET u_deleted=true WHERE u_id=?")
@Where(clause = "u_deleted = false")
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

    @Column(name = "u_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "u_deleted")
    private Boolean deleted;

    //    @Column(name = "technology")
//    private List<Technology> techTechnology;

//    @Column(name = "intership_list")
//    private List<UserInternshipDTO> listOfInternships;
    //private List<LocalDateTime> freeInterviewDates;
}