package by.exadel.internship.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@Entity
@Data
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "cntr_id")
    private UUID id;

    @Column(name = "cntr_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    Set<City> citySet;

    public void addCityToCountry(City city){
        if (citySet == null){
            citySet = new TreeSet<>();
        }
        citySet.add(city);
        city.setCountry(this);
    }
}
