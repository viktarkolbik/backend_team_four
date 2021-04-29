package by.exadel.internship.entity.location;

import by.exadel.internship.entity.Internship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"internships"})
public class Location {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "location_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "l_county_id")
    private Country country;

    @OneToOne
    @JoinColumn(name = "l_city_id")
    private City city;

    @ManyToMany(mappedBy = "locationList")
    private List<Internship> internships;
}

