package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.repository.InterviewRepository;
import by.exadel.internship.service.FormService;
import by.exadel.internship.service.InterviewService;
import by.exadel.internship.service.UserService;
import by.exadel.internship.service.UserTimeSlotService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    private static final String SIMPLE_CLASS_NAME = InterviewService.class.getSimpleName();

    private final InterviewRepository interviewRepository;
    private final InterviewMapper mapper;
    private final UserTimeSlotService userTimeSlotService;
    private final FormService formService;
    private final UserService userService;


    @Override
    public List<InterviewDTO> getAllByUserId(UUID userId, UserRole userRole) {
        List<Interview> interviews;
        switch (userRole){
            case ADMIN:{
                interviews = interviewRepository.findAllByAdmin(userId);
                break;
            }
            case TECH_EXPERT:{
                interviews = interviewRepository.findAllByTechSpecialist(userId);
                break;
            }
            default:{
                throw new InappropriateRoleException("Inappropriate role","user.role.invalid");
            }
        }
        return mapper.mapToInterviewDTO(interviews);
    }


    @Override
    public void saveInterviewForForm(UUID formId, InterviewDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        formFullDTO.setInterview(interviewDTO);
        if (interviewDTO.getTechSpecialist() == null && interviewDTO.getAdmin() != null){
            formFullDTO.setFormStatus(FormStatus.ADMIN_INTERVIEW_ASSIGNED);
            deleteTime(interviewDTO.getAdminInterviewDate(), interviewDTO.getAdmin());
            formService.updateForm(formFullDTO);
            log.info("Save HR interview date");
            return;
        }
        if (interviewDTO.getTechSpecialist() != null){
            formFullDTO.setFormStatus(FormStatus.TECH_INTERVIEW_ASSIGNED);
            deleteTime(interviewDTO.getTechInterviewDate(), interviewDTO.getTechSpecialist());
            formService.updateForm(formFullDTO);
            log.info("Save Tech Expert interview date");
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't has need Status");
    }

    private void deleteTime(LocalDateTime localDateTime, UUID userId) {
        UserDTO userDTO = userService.getById(userId);
        List<UserTimeSlotDTO> userTimeSlotDTOList = userDTO.getUserTimeSlots()
                .stream()
                .filter(userTimeSlotDTO -> userTimeSlotDTO.getStartDate()
                        .equals(localDateTime)).collect(Collectors.toList());
        userTimeSlotService.deletedById(userTimeSlotDTOList.get(0).getId());
    }

    @Override
    public void rewriteInterviewTime(UUID formId, InterviewDTO interviewDTO) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save Form with Interview");
        FormFullDTO formFullDTO = formService.getById(formId);
        if (formFullDTO.getFormStatus().equals(FormStatus.ADMIN_INTERVIEW_ASSIGNED)) {
            InterviewDTO restoreInterviewDTO = formFullDTO.getInterview();
            restoreTime(restoreInterviewDTO.getAdminInterviewDate(), restoreInterviewDTO.getAdmin());

            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(interviewDTO.getAdminInterviewDate(),interviewDTO.getAdmin());
            return;
        }
        if (formFullDTO.getFormStatus().equals(FormStatus.TECH_INTERVIEW_ASSIGNED)) {
            InterviewDTO restoreInterviewDTO = formFullDTO.getInterview();
            restoreTime(restoreInterviewDTO.getTechInterviewDate(), restoreInterviewDTO.getTechSpecialist());

            formFullDTO.setInterview(interviewDTO);
            formService.updateForm(formFullDTO);

            deleteTime(interviewDTO.getTechInterviewDate(),interviewDTO.getTechSpecialist());
            return;
        }
        throw new InappropriateRoleException("Form with uuid = " + formFullDTO.getId() + " doesn't have required status",
                "form.fromStatus.invalid");
    }

    private void restoreTime(LocalDateTime restoreLocalDateTime, UUID userId) {
        UserTimeSlotWithUserIdDTO restoreTime = new UserTimeSlotWithUserIdDTO();
        restoreTime.setStartDate(restoreLocalDateTime);
        restoreTime.setUserId(userId);
        userTimeSlotService.restoreUserTime(restoreTime);
    }


}
