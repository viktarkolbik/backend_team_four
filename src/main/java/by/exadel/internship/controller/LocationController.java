package by.exadel.internship.controller;

import by.exadel.internship.dto.locationDTO.CityDTO;
import by.exadel.internship.dto.locationDTO.CountryDTO;
import by.exadel.internship.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
@Api(tags = "Endpoints for Location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/countries")
    @ApiOperation("return list of countries")
    public List<CountryDTO> getAllCountries() {
        return locationService.getAllCountries();
    }

    @GetMapping("/cities")
    @ApiOperation("return list of cities")
    public List<CityDTO> getAllCities() {
        return locationService.getAllCities();
    }

    @GetMapping("/countries/name")
    @ApiOperation("return country by name")
    public CountryDTO getCountryByName(@RequestParam(value = "name") String name){
        return locationService.getCountryByName(name);
    }
    @GetMapping("/cities/name")
    @ApiOperation("return city by name")
    public CityDTO getCityByName(@RequestParam(value = "name") String name){
        return locationService.getCityByName(name);
    }
}