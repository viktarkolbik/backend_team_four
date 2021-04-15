package by.exadel.internship.entity;

import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "form")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE form SET fm_deleted=true WHERE fm_id=?")
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
    
    @Column(name = "fm_deleted")
    private Boolean deleted;

    @Column(name = "fm_english_level")
    @Enumerated(value = EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.EnumTypePostgreSQL")
    private EnglishLevel englishLevel;

    @Column(name = "fm_form_status")
    @Enumerated(value = EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.EnumTypePostgreSQL")
    private FormStatus formStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_id")
    private Interview interview;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fm_id")
    private List<TimeForCall> timeForCallList;

}