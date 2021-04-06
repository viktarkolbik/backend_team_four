package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "form")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Form {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "fm_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "fm_firstname")
    private String firstName;

    @Column(name = "fm_lastname")
    private String lastName;

    @Column(name = "fm_middlename")
    private String middleName;

    @Column(name = "fm_email")
    private String email;

    @Column(name = "fm_phone_number")
    private String phoneNumber;

    @Column(name = "fm_skype")
    private String skype;

    @Column(name = "fm_country")
    private String country;

    @Column(name = "fm_city")
    private String city;

    @Column(name = "fm_experience")
    private String experience;

    @Column(name = "fm_education")
    private String education;

    @Column(name = "fm_file_path")
    private String filePath;

    @Column(name = "fm_primary_skill")
    private String primarySkill;

//    private InterviewDTO interview;
//    private FormStatus formStatus;
//    //    this field is questionable
//    private TimeForCallDTO timeForCall;
}