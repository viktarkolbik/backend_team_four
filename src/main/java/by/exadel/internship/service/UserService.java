package by.exadel.internship.service;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.entity.user.User;
import by.exadel.internship.exception_handing.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        return userMapper.toUserDTO(user);
    }
}