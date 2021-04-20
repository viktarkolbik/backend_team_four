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

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<UserDTO> getAll() {

        MDC.put("className", UserService.class.getSimpleName());
        log.info("Try to get all users with skill");

        List<User> userList = userRepository.findAllWithSkill();

        log.info("Try get list of UserDTO");

        List<UserDTO> userDTOList = mapper.map(userList);

        log.info("Successfully list of UserDTO");

        return userDTOList;
    }

    public UserDTO getById(UUID id) {

        log.info("Try to get  user by id: {} with skills", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        log.info("Try get UserDTO from User");

        UserDTO  userDTO = mapper.toUserDTO(user);

        log.info("UserDTO got successfully");

        return userDTO;
    }

    public void deleteUserById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to delete User with uuid: {}", uuid);
        userRepository
                .findByIdAndDeletedFalse(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.deleteById(uuid);
        log.info("Successfully deleted User with uuid: {}", uuid);
    }

    public void restoreUserById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to restore User with uuid: {}", uuid);
        userRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.activateUserById(uuid);
        log.info("Successfully restore User with uuid: {}", uuid);
    }

    public List<UserDTO> getAllDeleted() {
        putClassNameInMDC();
        log.info("Try to get List of deleted user");
        List<User> userList = userRepository.findAllByDeletedTrue();
        log.info("Return List of deletes user");
        return mapper.map(userList);
    }

    private void putClassNameInMDC() {
        MDC.put("className", UserService.class.getSimpleName());
    }
}