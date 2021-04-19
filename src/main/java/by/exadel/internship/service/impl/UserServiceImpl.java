package by.exadel.internship.service.impl;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.repository.UserRepository;
import by.exadel.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;
    private final UserMapper USER_MAPPER;

    public List<UserDTO> getAll() {

        MDC.put("className", UserService.class.getSimpleName());
        log.info("Try to get all users with skill");

        List<User> userList = USER_REPOSITORY.findAllWithSkill();

        log.info("Try get list of UserDTO");

        List<UserDTO> userDTOList = USER_MAPPER.map(userList);

        log.info("Successfully list of UserDTO");

        return userDTOList;
    }

    public UserDTO getById(UUID id) {

        log.info("Try to get  user by id: {} with skills", id);

        User user = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        log.info("Try get UserDTO from User");

        UserDTO  userDTO = USER_MAPPER.toUserDTO(user);

        log.info("UserDTO got successfully");

        return userDTO;
    }
}