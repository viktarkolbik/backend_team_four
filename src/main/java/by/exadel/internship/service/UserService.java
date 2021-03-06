package by.exadel.internship.service;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.dto.user.UserFullDTO;
import by.exadel.internship.dto.user.UserInfoDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO getById(UUID id);

    UserInfoDTO getSimpleUserById(UUID id);

    List<UserDTO> getUsersByRoleAndInternshipId(UUID internshipId, UserRole role);

    void deleteUserById(UUID uuid);

    void restoreUserById(UUID uuid);

    void updateTimeSlot(UUID userId, List<UserTimeSlotDTO> userTimeSlotDTOList);

    List<UserDTO> getAllDeleted();

    List<UserDTO> getAllByUserRole(UserRole userRole);

    UserFullDTO getFullUserById(UUID userId);

    List<UserDTO> getUsersBySkills(List<Skill> skills);
}
