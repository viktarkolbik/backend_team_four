package by.exadel.internship.entity;
import by.exadel.internship.dto.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Entity
@Table(name = "techUser")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TechUser extends User {
    @Column(name = "technology")
    private Technology techTechnology;
}