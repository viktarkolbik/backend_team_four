package by.exadel.internship.controller;

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

    @GetMapping
    @ApiOperation("Return list of users")
    public List<UserDTO> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/userId")
    @ApiOperation("Return user by id")
    public UserDTO getUserById(@RequestParam UUID userId) {
        return userService.getById(userId);
    }

    @GetMapping("/internshipId")
    @ApiOperation("Return users by internship id and role")
    public List<UserDTO> getUserByInternshipId(@RequestParam("internshipId") UUID internshipId, UserRole role) {
        return userService.getUsersByRoleAndInternshipId(internshipId, role);
    }

}