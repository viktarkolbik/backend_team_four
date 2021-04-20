package by.exadel.internship.entity.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
public class City implements Comparable<City>{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "city_id")
    private UUID id;

    @Column(name = "city_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_cntr_id")
    private Country country;

    @Override
    public int compareTo(City o) {
        return this.getName().compareTo(o.getName());
    }
}
