package by.exadel.internship.service;
import by.exadel.internship.dto.userDTO.UserDTO;
import by.exadel.internship.entity.user.User;
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

    public UserDTO getById(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User with id " + uuid + " not found"));
        return userMapper.toUserDTO(user);
    }
}