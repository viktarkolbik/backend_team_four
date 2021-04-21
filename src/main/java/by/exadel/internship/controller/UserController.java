package by.exadel.internship.controller;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.service.UserService;
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
@RequestMapping("/users")
@Api(tags = "Endpoints for Users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Return list of users")
    public List<UserDTO> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    @ApiOperation("Return user by id")
    public UserDTO getUserById(@PathVariable UUID userId) {
        return userService.getById(userId);
    }

    @GetMapping("/internship/{internshipId}")
    @ApiOperation("Return administrators by internship id")
    public List<UserDTO> getUserByInternshipId(@PathVariable UUID internshipId) {
        return userService.getUsersWithRoleAdminByInternshipId(internshipId);
    }

}