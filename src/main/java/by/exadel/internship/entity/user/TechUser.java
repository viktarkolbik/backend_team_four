package by.exadel.internship.entity.user;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Entity
@Table(name = "techUser")
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("Tech_User")
public class TechUser extends User {
//    @Column(name = "technology")
//    private Technology techTechnology;
}