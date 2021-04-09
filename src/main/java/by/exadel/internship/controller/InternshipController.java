package by.exadel.internship.controller;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.service.InternshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internships")
@Api(tags = "Endpoints for Internship")
public class InternshipController {
    private final InternshipService internshipService;

    @GetMapping
    @ApiOperation("return list of internships")
    public List<GuestInternshipDTO> getInternshipList(@RequestParam(value = "isDeleted",
            required = false, defaultValue = "false") Boolean isDeleted) {
        return internshipService.getAll(isDeleted);
    }

    @GetMapping("/{internshipId}")
    @ApiOperation("return internship by id")
    public GuestInternshipDTO getInternshipById(@PathVariable UUID internshipId) {

        return internshipService.getById(internshipId);

    }

    @DeleteMapping("/{internshipId}")
    public String deleteInternship(@PathVariable UUID internshipId){
        internshipService.deleteInternship(internshipId);
        return "Done";
    }

}
