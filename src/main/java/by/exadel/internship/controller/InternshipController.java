package by.exadel.internship.controller;


import by.exadel.internship.dto.InternshipDTO;
import by.exadel.internship.dto.LocationDTO;
import by.exadel.internship.dto.enums.InternshipFormat;
import by.exadel.internship.dto.enums.Technology;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/internships")
@Api(tags = "Endpoints for Internship")
public class InternshipController {

    private static List<LocationDTO> locationList = new ArrayList<>();
    private static List<Technology> technologyList = new ArrayList<>();
    private static List<InternshipDTO> internshipDTOList = new ArrayList<>();
    private static LocationDTO firstLocationDTO = new LocationDTO();
    private static LocationDTO secondLocationDTO = new LocationDTO();

    static {

        firstLocationDTO.setCountry("country");
        firstLocationDTO.setCity("city");

        secondLocationDTO.setCountry("country_two");
        secondLocationDTO.setCity("city_two");

        locationList.add(firstLocationDTO);
        locationList.add(secondLocationDTO);

        technologyList.add(Technology.JAVA);
        technologyList.add(Technology.JS);

    }

    private static InternshipDTO internshipDTO = InternshipDTO.builder()
            .name("test internship 1")
            .startDate(LocalDate.now().plusMonths(1))
            .endDate(LocalDate.now().plusMonths(3))
            .techSkills("Java, JS")
            .countryList(locationList)
            .InternshipFormat(InternshipFormat.ONLINE)
            .technologyList(technologyList)
            .description("some description about internship")
            .requirements("some requirements for internship")
            .capacity(50)
            .registrationStartDate(LocalDate.now())
            .registrationEndDate(LocalDate.now().plusDays(14))
            .build();


    @ApiOperation("return list of internships")
    public List<InternshipDTO> getInternshipList() {

        internshipDTOList.add(internshipDTO);

        return internshipDTOList;
    }

    @GetMapping("/{internshipId}")
    @ApiOperation("return internship by id")
    public InternshipDTO getInternship(@PathVariable String internshipId) {

        return internshipDTO;
    }

}
