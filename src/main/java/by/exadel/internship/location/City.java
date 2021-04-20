package by.exadel.internship.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "city_cntr_id")
    Country country;


}
