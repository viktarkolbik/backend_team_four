package by.exadel.internship.service.impl;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception.NotFoundException;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.repository.UserRepository;
import by.exadel.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;
    private final UserMapper USER_MAPPER;

    @Override
    public List<UserDTO> getAll() {

        List<User> userList = USER_REPOSITORY.findAll();

        return USER_MAPPER.map(userList);

    }

    @Override
    public UserDTO getById(UUID id) {

        User user = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        return USER_MAPPER.toUserDTO(user);
    }
}