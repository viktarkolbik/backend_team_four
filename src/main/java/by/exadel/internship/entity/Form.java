package by.exadel.internship.entity;

import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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

    @OneToOne
    @JoinColumn(name = "fm_country_id")
    private Country country;

    @OneToOne
    @JoinColumn(name = "fm_city_id")
    private City city;

    @Column(name = "fm_experience")
    private String experience;

    @Column(name = "fm_education")
    private String education;

    @Column(name = "fm_file_path")
    private String filePath;

    @Column(name = "fm_primary_skill")
    private String primarySkill;
    
    @Column(name = "fm_deleted")
    private boolean deleted;

    @Column(name = "fm_english_level")
    @Enumerated(value = EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private EnglishLevel englishLevel;

    @Column(name = "fm_form_status")
    @Enumerated(value = EnumType.STRING)
    @Type(type = "by.exadel.internship.mapper.enum_mapper.EnumTypePostgreSQL")
    private FormStatus formStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fm_i_id")
    private Interview interview;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fm_id")
    private List<TimeForCall> timeForCallList;

    @Column(name = "fm_inship_id")
    private UUID internshipId;
}