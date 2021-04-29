package by.exadel.internship.service;

import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    List<CountryDTO> getAllCountries();

    List<CityDTO> getCitiesByCountryId(UUID countryId);
}
