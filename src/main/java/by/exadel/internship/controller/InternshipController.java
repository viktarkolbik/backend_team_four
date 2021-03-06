package by.exadel.internship.controller;

import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.service.InternshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/internships")
@Api(tags = "Endpoints for Internship")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @GetMapping
    @ApiOperation("return list of internships")
    public List<BaseInternshipDTO> getInternshipList() {
        return internshipService.getAll();
    }

    @GetMapping("/{internshipId}")
    @ApiOperation("return internship by id")
    public BaseInternshipDTO getInternshipById(@PathVariable("internshipId") UUID internshipId, boolean fullRepresentation) {

        return fullRepresentation
                ? internshipService.getUserRepresentationOfInternshipById(internshipId)
                : internshipService.getGuestRepresentationOfInternshipById(internshipId);

    }

    @AdminAccessControl
    @GetMapping("/historical")
    @ApiOperation("Return List of deleted Internships")
    public List<UserInternshipDTO> getDeletedInternshipList() {
        return internshipService.getAllDeleted();
    }

    @AdminAccessControl
    @GetMapping("/historical/{internshipId}")
    @ApiOperation("Return deleted Internship by ID")
    public UserInternshipDTO getDeletedInternship(@PathVariable UUID internshipId) {
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
    public UserInternshipDTO restoreInternship(@PathVariable UUID internshipId) {
        return internshipService.restoreInternshipById(internshipId);
    }

    @AdminAccessControl
    @PostMapping
    @ApiOperation("Save new Internship In DB")
    public UserInternshipDTO saveInternship(@RequestBody UserInternshipDTO internshipDTO) {
        return internshipService.saveInternship(internshipDTO);
    }

    @AdminAccessControl
    @GetMapping("/skills")
    @ApiOperation("return list of skills")
    public List<Skill> getSkillList() {
        return Arrays.asList(Skill.values());
    }

    @SuperAdminAccessControl
    @PutMapping("/{internshipId}")
    @ApiOperation("update internship")
    public UserInternshipDTO updateInternship(@PathVariable(name = "internshipId") UUID internshipId, @RequestBody UserInternshipDTO internshipDTO) {
        return internshipService.update(internshipId, internshipDTO);
    }

    @SuperAdminAccessControl
    @PostMapping("/{internshipId}/users/assign")
    @ApiOperation("Add User to Internship")
    public void assignUser(@RequestParam(name = "userIds") List<UUID> userIds, @PathVariable(name = "internshipId") UUID internshipId) {
        internshipService.assignUsers(userIds, internshipId);
    }

    @SuperAdminAccessControl
    @PutMapping("/{internshipId}/users/replace")
    @ApiOperation("Update User to Internship")
    public UserInternshipDTO replaceUsersAssignedToInternship(@RequestParam(name = "userIds") Set<UUID> userIds, @PathVariable(name = "internshipId") UUID internshipId) {
        return internshipService.replaceUsersAssignedToInternship(userIds, internshipId);
    }
}