package by.exadel.internship.service;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception.NotFoundException;
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

        List<User> userList = userRepository.findAllWithSkill();

        return userMapper.map(userList);

    }

    public UserDTO getById(UUID uuid) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found"));

        return userMapper.toUserDTO(user);
    }
}