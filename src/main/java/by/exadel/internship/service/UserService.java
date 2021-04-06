package by.exadel.internship.service;
import by.exadel.internship.dto.enums.Technology;
import by.exadel.internship.dto.userDTO.TechUserDTO;
import by.exadel.internship.dto.userDTO.UserDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.TechUser;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception.UserNotFoundException;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        List<User> userList = userRepository.findAll();
        return userMapper.map(userList);
    }

    public List<UserDTO> getByRole(UserRole userRole) {
        return userMapper.map(userRepository.getByUserRole(userRole));
    }

    public List<TechUserDTO> getTechExpertsByTechnology(UserRole userRole, Technology technology) {
        List<TechUser> users = userRepository.getByUserRoleAAndTechTechnology(userRole, technology);
        return userMapper.mapToTech(users);
    }

    public UserDTO getById(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User with id " + uuid + " not found"));
        return userMapper.toUserDTO(user);
    }
}