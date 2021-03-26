package by.exadel.internship.team_four.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipLocation {
    private String country;
    private String city;
    private String officeAddress;

}
