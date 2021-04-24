package by.exadel.internship.entity.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "cityList")
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "cntr_id")
    private UUID id;

    @Column(name = "cntr_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<City> cityList;

    public Country(String name) {
        this.name = name;
    }
}
