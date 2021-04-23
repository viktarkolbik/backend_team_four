package by.exadel.internship.controller;

import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.annotation.UserAccessControl;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = "Endpoints for Users")
public class UserController {

    private final UserService userService;

    @UserAccessControl
    @GetMapping("/{userId}")
    @ApiOperation("Return user by id")
    public UserDTO getUserById(@PathVariable("userId") UUID userId) {
        return userService.getById(userId);
    }

    @SuperAdminAccessControl
    @GetMapping
    @ApiOperation("Return users by internship id and role")
    public List<UserDTO> getUserByInternshipId(@RequestParam("internshipId") UUID internshipId, UserRole role) {
        return userService.getUsersByRoleAndInternshipId(internshipId, role);
    }

    @SuperAdminAccessControl
    @DeleteMapping("/{userId}")
    @ApiOperation("Delete user")
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
    }

    @SuperAdminAccessControl
    @PutMapping("/{userId}/restore")
    @ApiOperation("do active deleted User")
    public void restoreUser(@PathVariable UUID userId) {
        userService.restoreUserById(userId);
    }

    @SuperAdminAccessControl
    @GetMapping("/historical")
    @ApiOperation("Return list of deleted users")
    public List<UserDTO> getDeletedUserList() {
        return userService.getAllDeleted();
    }
}