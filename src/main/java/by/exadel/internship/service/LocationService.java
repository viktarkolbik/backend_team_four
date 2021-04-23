package by.exadel.internship.service;

import by.exadel.internship.dto.locationDTO.CityDTO;
import by.exadel.internship.dto.locationDTO.CountryDTO;

import java.util.List;

public interface LocationService {

    List<CountryDTO> getAllCountries();

    CountryDTO getCountryByName(String name);

    List<CityDTO> getCityListByCountryName(String countryName, String cityName);
}
