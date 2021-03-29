package by.exadel.internship.team_four.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipLocation {
    private UUID id;
    private String country;
    private String city;
}
