package by.exadel.internship.service;

import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {

        MDC.put("className", UserService.class.getSimpleName());
        log.info("Try to get all users with skill");

        List<User> userList = userRepository.findAllWithSkill();

        System.out.println();
        for (User u:userList) {
            System.out.println();
            System.out.println(u.getFirstName()+ " " + u.getLastName());
        }

        log.info("Successfully list of users");
        log.info("Try get list of UserDTO");

        List<UserDTO> userDTOList = userMapper.map(userList);

        log.info("Successfully list of UserDTO");

        return userDTOList;
    }

    public UserDTO getById(UUID uuid) {

        log.info("Try to get  user by id:" + uuid + " with skills");

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found"));

        log.info("User got successfully");
        log.info("Try get UserDTO from User");

        UserDTO  userDTO = userMapper.toUserDTO(user);

        log.info("UserDTO got successfully");

        return userDTO;
    }
}