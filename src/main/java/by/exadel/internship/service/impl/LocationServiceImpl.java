package by.exadel.internship.service.impl;

import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final LocationMapper locationMapper;

    private void putClassNameInMDC(){MDC.put("className", LocationServiceImpl.class.getSimpleName());}

    public List<CountryDTO> getAllCountries() {
        putClassNameInMDC();
        log.info("Try to get all countries");
        List<Country> countries = countryRepository.findAll();
        log.info("Try to get list CountryDTO");
        List<CountryDTO> countryDTOList = locationMapper.mapToListCountryDTO(countries);
        log.info("Successfully get list of CountryDTO");
        return countryDTOList;
    }

    public List<CityDTO> getCitiesByCountryId(UUID countryId) {
        putClassNameInMDC();
        log.info("Try to get list of cities with id country = {} ", countryId);
        List<City> cityListByCountryId = cityRepository.findAllByCountryId(countryId);
        log.info("Try to get list CityDTO");
        List<CityDTO> cityDTOList = locationMapper.mapToListCityDTO(cityListByCountryId);
        log.info("Successfully get list of CityDTO by country id = {} ", countryId);
        return cityDTOList;
    }
}
