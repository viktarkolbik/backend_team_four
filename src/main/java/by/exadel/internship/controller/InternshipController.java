package by.exadel.internship.controller;


import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.dto.internship.GuestInternshipDTO;
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
    public List<GuestInternshipDTO> getInternshipList() {
        return internshipService.getAll();
    }

    @GetMapping("/{internshipId}")
    @ApiOperation("return internship by id")
    public GuestInternshipDTO getInternshipById(@PathVariable("internshipId") UUID internshipId) {
        return internshipService.getById(internshipId);
    }

    @AdminAccessControl
    @GetMapping("/historical")
    @ApiOperation("Return List of deleted Internships")
    public List<GuestInternshipDTO> getDeletedInternshipList() {
        return internshipService.getAllDeleted();
    }

    @AdminAccessControl
    @GetMapping("/historical/{internshipId}")
    @ApiOperation("Return deleted Internship by ID")
    public GuestInternshipDTO getDeletedInternship(@PathVariable UUID internshipId) {
        return internshipService.getDeletedInternshipById(internshipId);
    }

    @SuperAdminAccessControl
    @DeleteMapping("/{internshipId}")
    @ApiOperation("Delete Internship by ID")
    public void deleteInternship(@PathVariable UUID internshipId) {
        internshipService.deleteInternshipById(internshipId);
    }

    @SuperAdminAccessControl
    @PutMapping("/{internshipId}/restore")
    @ApiOperation("Restore deleted Internships")
    public GuestInternshipDTO restoreInternship(@PathVariable UUID internshipId) {
        return internshipService.restoreInternshipById(internshipId);
    }

    @AdminAccessControl
    @PostMapping
    @ApiOperation("Save new Internship In DB")
    public GuestInternshipDTO saveInternship(@RequestBody GuestInternshipDTO internshipDTO) {
        return internshipService.saveInternship(internshipDTO);
    }
}
