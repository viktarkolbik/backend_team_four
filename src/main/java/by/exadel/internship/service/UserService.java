package by.exadel.internship.service;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getById(UUID id);

    List<UserDTO> getUsersByRoleAndInternshipId(UUID internshipId, UserRole role);

    void deleteUserById(UUID uuid);

    void restoreUserById(UUID uuid);

    List<UserDTO> getAllDeleted();

    List<UserDTO> getAllByUserRole(UserRole userRole);

}
