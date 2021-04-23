package by.exadel.internship.service.impl;
import by.exadel.internship.dto.locationDTO.CityDTO;
import by.exadel.internship.dto.locationDTO.CountryDTO;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import by.exadel.internship.repository.location.CityRepository;
import by.exadel.internship.repository.location.CountryRepository;
import by.exadel.internship.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final LocationMapper locationMapper;

    public List<CountryDTO> getAllCountries() {
        MDC.put("className", LocationServiceImpl.class.getSimpleName());
        log.info("Try to get all countries");
        List<Country> countries = countryRepository.findAll();
        log.info("Try to get list CountryDTO");
        List<CountryDTO> countryDTOList = locationMapper.mapToListCountryDTO(countries);
        log.info("Successfully get list of CountryDTO");
        return countryDTOList;
    }

    public List<CityDTO> getAllCities() {
        MDC.put("className", LocationServiceImpl.class.getSimpleName());
        log.info("Try to get all cities");
        List<City> cities = cityRepository.findAll();
        log.info("Try to get list CityDTO");
        List<CityDTO> cityDTOList = locationMapper.mapToListCityDTO(cities);
        log.info("Successfully get list of CityDTO");
        return cityDTOList;
    }

    public CountryDTO getCountryByName(String name) {
        Country country = countryRepository.findCountryByName(name)
                .orElseThrow(() -> new NotFoundException("There is no such country with name " + name));
        return locationMapper.toCountryDTO(country);
    }

    public CityDTO getCityByName(String name) {
        City city = cityRepository.findCityByName(name)
                .orElseThrow(() -> new NotFoundException("There is no such country with name " + name));
        return locationMapper.toCityDTO(city);
    }
}
