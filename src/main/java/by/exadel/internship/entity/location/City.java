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
public class City {

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

    @Column(name = "city_cntr_id")
    private UUID countryId;
}
