package by.exadel.internship.mapper.location_mapper;

import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.dto.location.LocationDTO;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.entity.location.Location;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Named(value = "city")
    CityDTO toCityDTO(City city);

    City toCityEntity(CityDTO cityDTO);

    @Named(value = "country")
    CountryDTO toCountryDTO(Country country);

    Country toCountryEntity(CountryDTO countryDTO);

    @IterableMapping(qualifiedByName = "country")
    List<CountryDTO> mapToListCountryDTO(List<Country> countryList);

    @IterableMapping(qualifiedByName = "city")
    List<CityDTO> mapToListCityDTO(List<City> cityList);


    @Named(value = "location")
    LocationDTO toLocationDTO(Location location);

    Location toLocationEntity(LocationDTO locationDTO);

    @IterableMapping(qualifiedByName = "location")
    List<LocationDTO> mapToListLocationDTO(List<Location> locationList);
}
