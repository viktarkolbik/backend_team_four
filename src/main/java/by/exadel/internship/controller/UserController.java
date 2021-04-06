package by.exadel.internship.controller;
import by.exadel.internship.dto.enums.Technology;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.userDTO.TechUserDTO;
import by.exadel.internship.dto.userDTO.UserDTO;
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

    @GetMapping("/{userId}")
    @ApiOperation("Return user by id")
    public UserDTO getUserById(@PathVariable UUID userId) {
        return userService.getById(userId);
    }

    @GetMapping("/users-by-role")
    @ApiOperation("Return list of users by role")
    public List<UserDTO> getUserListByRole(@RequestParam("userRole") UserRole userRole) {
        return userService.getByRole(userRole);
    }


    @GetMapping("/tech-users-by-technology")
    @ApiOperation("Return tech users  by technology")
    public List<TechUserDTO> getTechExpertsByTechnology(@RequestParam("userRole") UserRole userRole,
                                                        @RequestParam("technology") Technology technology) {
        return userService.getTechExpertsByTechnology(userRole, technology);
    }
}