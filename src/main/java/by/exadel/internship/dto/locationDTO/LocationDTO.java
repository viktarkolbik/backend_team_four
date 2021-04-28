package by.exadel.internship.dto.locationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    private CountryDTO country;
    private CityDTO city;

}
