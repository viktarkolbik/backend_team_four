package by.exadel.internship.service;

import by.exadel.internship.entity.User;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.dto.UserDTO;
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
        putClassNameInMDC();
        log.info("Try to get List of Users");
        List<User> userList = userRepository.findAllByDeletedFalse();
        log.info("Return List of User");
        return userMapper.map(userList);

    }

    public UserDTO getById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to get User with uuid = " + uuid);
        User user = userRepository
                .findByIdAndDeletedFalse(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        log.info("Return User with uuid = " + uuid);
        return userMapper.toUserDTO(user);
    }

    public void deleteUserById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to delete User with uuid = " + uuid);
        userRepository
                .findByIdAndDeletedFalse(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.deleteById(uuid);
        log.info("Successfully deleted User with uuid = " + uuid);
    }

    public void restoreUserById(UUID uuid) {
        putClassNameInMDC();
        log.info("Try to activate User with uuid = " + uuid);
        userRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.activateUserById(uuid);
        log.info("Successfully activate User with uuid = " + uuid);
    }

    public List<UserDTO> getAllDeleted() {
        putClassNameInMDC();
        log.info("Try to get List of deleted user");
        List<User> userList = userRepository.findAllByDeletedTrue();
        log.info("Return List of deletes user");
        return userMapper.map(userList);
    }

    private void putClassNameInMDC() {
        MDC.put("className", UserService.class.getSimpleName());
    }
}