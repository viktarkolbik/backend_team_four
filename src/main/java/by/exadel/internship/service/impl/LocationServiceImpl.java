package by.exadel.internship.service.impl;

import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.mapper.location_mapper.LocationMapper;
import by.exadel.internship.repository.location.CityRepository;
import by.exadel.internship.repository.location.CountryRepository;
import by.exadel.internship.service.FormService;
import by.exadel.internship.service.LocationService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private static final String SIMPLE_CLASS_NAME = LocationService.class.getSimpleName();
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final LocationMapper locationMapper;

    public List<CountryDTO> getAllCountries() {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all countries");

        List<Country> countries = countryRepository.findAll();

        log.info("Try to get list CountryDTO");

        List<CountryDTO> countryDTOList = locationMapper.mapToListCountryDTO(countries);

        log.info("Successfully get list of CountryDTO");

        return countryDTOList;
    }

    public List<CityDTO> getCitiesByCountryId(UUID countryId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get list of cities with id country = {} ", countryId);

        List<City> cityListByCountryId = cityRepository.findAllByCountryId(countryId);

        log.info("Try to get list CityDTO");

        List<CityDTO> cityDTOList = locationMapper.mapToListCityDTO(cityListByCountryId);

        log.info("Successfully get list of CityDTO by country id = {} ", countryId);

        return cityDTOList;
    }
}
