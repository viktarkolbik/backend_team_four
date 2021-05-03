package by.exadel.internship.entity;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"internships"})
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
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private UserRole userRole;

    @Column(name = "u_interview_time")
    private int interviewTime;

    @Column(name = "u_deleted")
    private boolean deleted;

    @Column(name = "us_name", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_skill", joinColumns = @JoinColumn(name = "us_u_id"))
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private List<Skill> skills;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Internship> internships;

    @OneToMany(mappedBy = "user")
    private List<UserTimeSlot> userTimeSlots;

}