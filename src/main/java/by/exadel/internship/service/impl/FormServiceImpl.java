package by.exadel.internship.service.impl;

import by.exadel.internship.dto.FeedbackRequest;
import by.exadel.internship.dto.FileInfoDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormInfoDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.user.UserDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.exception_handing.BadConditionException;
import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.repository.location.CityRepository;
import by.exadel.internship.repository.location.CountryRepository;
import by.exadel.internship.service.*;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class FormServiceImpl implements FormService {

    private static final String SIMPLE_CLASS_NAME = FormService.class.getSimpleName();
    private final FormMapper mapper;

    private final FormRepository formRepository;
    private final UserService userService;

    private final InternshipService internshipService;
    private final EmailService emailService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final FileService fileService;


    @Override
    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);

        if (file != null) {

            try {
                String fileURL = fileService.upload(file.getBytes(), file.getOriginalFilename());
                form.setFilePath(fileURL);
            } catch (IOException e) {
                throw new FileNotUploadException("File was not uploaded because: " + e.getMessage());
            }
            FormFullDTO createdForm = saveForm(form);

            log.info("Success to save form, id: {}", createdForm.getId());

            return createdForm;
        }

        FormFullDTO createdForm = saveForm(form);

        log.info("Success to save form, id: {}", createdForm.getId());

        return createdForm;
    }


    @Override
    public List<FormFullDTO> getAll() {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms");

        List<Form> formList = formRepository.findAllWithTimeForCallList();

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }

    @Override
    public FormFullDTO getById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get From with uuid = {}", formId);

        Form form = formRepository.findByIdAndDeletedFalse(formId).
                orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));

        log.info("Return formFullDTO with uuid = {}", formId);
        return mapper.toFormDto(form);
    }




    @Override
    public void updateFeedback(UUID formId, FeedbackRequest feedbackRequest) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to save feedback for Form with uuid = {}", formId);
        Form form = formRepository.findByIdAndDeletedFalse(formId).
                orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        UserDTO userDTO = userService.getById(feedbackRequest.getUserId());
        setFeedbackByUserRole(userDTO, form, feedbackRequest);
        log.info("Feedback was updating");

    }

    @Override
    @Transactional
    public void updateStatusById(UUID formId, FormStatus status) {
        log.info("Try to get form by form id: {}", formId);

        Form one = formRepository.getOne(formId);

        log.info("Try to set status");

        one.setFormStatus(status);

        log.info("Try to update status");

        formRepository.save(one);
    }


    @Override
    public FileInfoDTO getFileByFormId(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to download file by formId = {}", formId);

        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new NotFoundException(StringUtils
                        .join("Form with uuid = ", formId, " NotFound in DB")
                        , "form.uuid.invalid"));
        GuestInternshipDTO internshipDTO = internshipService
                .getGuestRepresentationOfInternshipById(form.getInternshipId());
        FileInfoDTO fileInfoDTO = fileService
                .download(form.getFilePath(), form.getLastName(), internshipDTO.getName());
        log.info("Return file like byte[] and some info about file");
        return fileInfoDTO;
    }


    @Transactional
    public void restoreFormById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to activate form with uuid: {}", formId);

        formRepository
                .findByIdAndDeletedTrue(formId)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        formRepository.activateFormById(formId);

        log.info("Successfully returned deleted Form with uuid: {}", formId);
    }


    @Override
    public void deleteById(UUID formId) {
        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to delete form with uuid: {} ", formId);

        formRepository
                .findByIdAndDeletedFalse(formId)
                .orElseThrow(() -> new NotFoundException("Form with uuid = " + formId +
                        " Not Found in DB", "form.uuid.invalid"));
        formRepository.deleteById(formId);

        log.info("Successfully deleted Form with uuid: {}", formId);
    }

    @Transactional
    @Override
    public List<FormInfoDTO> getAllByCondition(UUID internshipId, UUID userId) {

        checkCondition(internshipId, userId);

        return internshipId != null
                ? getAllByInternshipId(internshipId)
                : getAllByUserId(userId);
    }

    private List<FormInfoDTO> getAllByInternshipId(UUID internshipId) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms by internship id");

        List<Form> formList = formRepository.findAllByInternship(internshipId);

        log.info("Successfully list of formFullDTO");

        return mapper.mapToInfoDto(formList);

    }

    private List<FormInfoDTO> getAllByUserId(UUID userId) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get forms by user id: {}", userId);

        return mapper
                .mapToInfoDto(formRepository
                        .findAllByUserId(userId));
    }

    private void checkCondition(UUID internshipId, UUID userId) {

        if (internshipId == null && userId == null) {
            throw new BadConditionException("Both conditions are null");
        }
        if (internshipId != null && userId != null) {
            throw new BadConditionException("Both conditions are filled");
        }
    }

    private void setFeedbackByUserRole(UserDTO userDTO, Form form, FeedbackRequest feedbackRequest) {
        log.info("Set feedback in Interview By userRole");
        if (userDTO.getUserRole().equals(UserRole.ADMIN)) {
            if (form.getInterview().getAdmin().getId().equals(feedbackRequest.getUserId())) {
                form.getInterview().setAdminFeedback(feedbackRequest.getFeedback());
                form.setFormStatus(FormStatus.ADMIN_INTERVIEW_PASSED);
                formRepository.save(form);
                log.info("Added Admin feedback to Interview with uuid = {}", form.getInterview().getId());
            } else {
                throw new NotFoundException("Admin with uuid = "
                        + userDTO.getId() + " did not interview Form with uuid = "
                        + form.getId(), "user.uuid.invalid");
            }
        }
        if (userDTO.getUserRole().equals(UserRole.TECH_EXPERT)) {
            if (form.getInterview().getTechSpecialist().getId().equals(feedbackRequest.getUserId())) {
                form.getInterview().setTechFeedback(feedbackRequest.getFeedback());
                form.setFormStatus(FormStatus.TECH_INTERVIEW_PASSED);
                formRepository.save(form);
                log.info("Added Tech Expert feedback to Interview with uuid = {}", form.getInterview().getId());
            } else {
                throw new NotFoundException("Tech expert with uuid = "
                        + userDTO.getId() + " did not interview Form with uuid = "
                        + form.getId(), "user.uuid.invalid");
            }
        }
        if (userDTO.getUserRole().equals(UserRole.SUPER_ADMIN)) {
            throw new InappropriateRoleException("Super Admin can not set Feedback");
        }
    }

    private FormFullDTO saveForm(FormRegisterDTO formRegisterDTO) {

        UUID countryId = formRegisterDTO.getCountry().getId();
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException("Country with uuid = " + countryId +
                " Not Found in DB", "form.uuid.invalid"));
        UUID cityId = formRegisterDTO.getCity().getId();
        City city = cityRepository.findById(cityId).orElseThrow(() -> new NotFoundException("City with uuid = " + cityId +
                " Not Found in DB", "form.uuid.invalid"));

        Form form = mapper.toFormEntity(formRegisterDTO);

        form.setCountry(country);
        form.setCity(city);


        log.info("Save form, id: {} internshipId {} and status {}", form.getId(), form.getInternshipId(), FormStatus.REGISTERED);

        form.setFormStatus(FormStatus.REGISTERED);

        log.info("The form status is {}", FormStatus.REGISTERED);

        formRepository.save(form);

        FormFullDTO dto = mapper.toFormDto(form);

        dto.setSendEmail(emailService.sendFormSubmissionEmail(formRegisterDTO));
        return dto;
    }
}