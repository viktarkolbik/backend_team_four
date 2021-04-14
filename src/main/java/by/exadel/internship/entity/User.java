
package by.exadel.internship.entity;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE \"user\" SET u_deleted=true WHERE u_id=?")
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

    @Column(name="us_name", nullable=false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_skill", joinColumns = @JoinColumn(name = "us_u_id"))
    private Set<Skill> skills;

//    @Column(name = "intership_list")
//    private List<UserInternship> listOfInternships;
//    private List<LocalDateTime> freeInterviewDates;
}