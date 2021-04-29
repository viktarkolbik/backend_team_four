package by.exadel.internship.controller;

import by.exadel.internship.dto.location.CityDTO;
import by.exadel.internship.dto.location.CountryDTO;
import by.exadel.internship.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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
    @ApiOperation("return city list")
    public List<CityDTO> getCityList(@RequestParam(value = "countryId") UUID countryId){
        return locationService.getCitiesByCountryId(countryId);
    }
}