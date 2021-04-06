package by.exadel.internship.entity;

import by.exadel.internship.dto.enums.Technology;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table("techUser")
@SuperBuilder
public class TechUser extends User {
    @Column(name = "technology")
    private Technology techTechnology;
}