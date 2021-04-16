package by.exadel.internship.controller;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.service.InternshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internships")
@Api(tags = "Endpoints for Internship")
public class InternshipController {

    private final InternshipService INTERNSHIP_SERVICE;

    @GetMapping
    @ApiOperation("return list of internships")
    public List<GuestInternshipDTO> getInternshipList() {
        return INTERNSHIP_SERVICE.getAll();

    }

    @GetMapping("/{internshipId}")
    @ApiOperation("return internship by id")
    public GuestInternshipDTO getInternshipById(@PathVariable UUID internshipId) {

        return INTERNSHIP_SERVICE.getById(internshipId);

    }
}
