package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

    @Id
    @Column(name = "tech_name")
    private String name;

    @OneToMany(mappedBy="technology")
    private Set<Internship> internshipSet;

}
