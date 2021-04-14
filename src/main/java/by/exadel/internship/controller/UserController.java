package by.exadel.internship.controller;
import by.exadel.internship.dto.UserDTO;
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

    @DeleteMapping("/{userId}")
    @ApiOperation("Delete user")
    public void deleteUser(@PathVariable UUID userId){
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}/restore")
    @ApiOperation("do active deleted User")
    public void restoreUser(@PathVariable UUID userId){
        userService.restoreUserById(userId);
    }

    @GetMapping("/historical")
    @ApiOperation("Return list of deleted users")
    public List<UserDTO> getDeletedUserList() {
        return userService.getAllDeleted();
    }
}