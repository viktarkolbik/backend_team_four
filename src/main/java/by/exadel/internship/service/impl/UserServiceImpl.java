package by.exadel.internship.service.impl;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.dto.user.UserFullDTO;
import by.exadel.internship.dto.user.UserInfoDTO;
import by.exadel.internship.entity.User;
import by.exadel.internship.entity.UserTimeSlot;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.UserMapper;
import by.exadel.internship.mapper.UserTimeSlotMapper;
import by.exadel.internship.repository.UserRepository;
import by.exadel.internship.repository.UserTimeSlotRepository;
import by.exadel.internship.service.UserService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTimeSlotRepository userTimeSlotRepository;
    private final UserMapper mapper;
    private final UserTimeSlotMapper userTimeSlotMapper;

    private static final String SIMPLE_CLASS_NAME = UserService.class.getSimpleName();

    @Override
    public UserDTO getById(UUID id) {
        log.info("Try to get  user by id only with current timeslots: {} with skills", id);

        User user = userRepository.findUserByIdWithCurrentTimeSlots(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));


        log.info("Try get UserDTO from User");

        UserDTO userDTO = mapper.toUserDTO(user);

        log.info("UserDTO got successfully");

        Comparator<UserTimeSlotDTO> comparator = Comparator.comparing(UserTimeSlotDTO::getStartDate);

        userDTO.getUserTimeSlots().sort(comparator);

        return userDTO;
    }

    @Override
    public UserInfoDTO getSimpleUserById(UUID id) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get simple user by id: {} with skills", id);

        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        log.info("Try get UserInfoDTO from User");

        UserInfoDTO userInfoDTO = mapper.toUserInfo(user);

        log.info("UserInfoDTO got successfully");

        return userInfoDTO;
    }

    @Override
    public List<UserDTO> getUsersByRoleAndInternshipId(UUID internshipId, UserRole role) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get  users by id: {} with skills and  role: {}", internshipId, role);

        List<User> userList = userRepository.findAllWithSkillByInternshipId(internshipId, role);

        log.info("Try get UserDTOs from users");

        List<UserDTO> userDTOList = mapper.map(userList);

        log.info("UserDTOs got successfully");

        return userDTOList;
    }

    @Override
    public void deleteUserById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to delete User with uuid: {}", uuid);
        userRepository
                .findByIdAndDeletedFalse(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.deleteById(uuid);

        log.info("Successfully deleted User with uuid: {}", uuid);
    }

    @Transactional
    @Override
    public void restoreUserById(UUID uuid) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to restore User with uuid: {}", uuid);

        userRepository
                .findByIdAndDeletedTrue(uuid)
                .orElseThrow(() -> new NotFoundException("User with id " + uuid + " not found", "uuid.invalid"));
        userRepository.activateUserById(uuid);

        log.info("Successfully restore User with uuid: {}", uuid);
    }

    @Override
    public void updateTimeSlot(UUID userId, List<UserTimeSlotDTO> userTimeSlotDTOList) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to update user time slot");

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
        List<UserTimeSlot> userTimeSlot = userTimeSlotMapper.map(userTimeSlotDTOList);
        userTimeSlot.forEach(timeSlot -> timeSlot.setUser(user));
        userTimeSlotRepository.saveAll(userTimeSlot);

        log.info("Successfully updated user user time slot");
    }

    @Override
    public List<UserDTO> getAllDeleted() {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get List of deleted user");

        List<User> userList = userRepository.findAllByDeletedTrue();

        log.info("Return List of deletes user");
        return mapper.map(userList);
    }

    @Override
    public List<UserDTO> getAllByUserRole(UserRole userRole) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get List of user with userRole : {}", userRole);

        List<User> users = userRepository.findAllByUserRole(userRole);

        log.info("Return List of user");
        return mapper.map(users);
    }

    @Override
    public UserFullDTO getFullUserById(UUID userId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to update user time slot");

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));

        log.info("UserDTOs got successfully");
        return mapper.toUserFull(user);
    }

    public List<UserDTO> getUsersBySkills(List<Skill> skills) {

        Set<Skill> enumListToString = skills.stream().collect(Collectors.toSet());

        log.info("Try to get set of user by skills : {}", skills);

        List<User> usersWithSkills = userRepository.getUsersBySkillsInAndDeletedFalse(enumListToString);

        log.info("Return set of user by skill");
        return mapper.map(usersWithSkills);
    }
}