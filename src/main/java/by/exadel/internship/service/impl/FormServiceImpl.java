package by.exadel.internship.service.impl;

import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.exception_handing.FileNotUploadException;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.FormMapper;
import by.exadel.internship.repository.FormRepository;
import by.exadel.internship.repository.location.CityRepository;
import by.exadel.internship.repository.location.CountryRepository;
import by.exadel.internship.service.EmailService;
import by.exadel.internship.service.FileService;
import by.exadel.internship.service.FormService;
import by.exadel.internship.util.MDCLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final EmailService emailService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    private final FileService fileService;

    public FormFullDTO process(FormRegisterDTO form, MultipartFile file) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);

        if (file != null) {

            try {
                String fileURL = fileService.upload(file.getBytes(),file.getOriginalFilename());
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

    private FormFullDTO saveForm(FormRegisterDTO formRegisterDTO) {

        UUID countryId = formRegisterDTO.getCountry().getId();
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException("City with uuid = " + countryId +
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

    public List<FormFullDTO> getAll() {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms");

        List<Form> formList = formRepository.findAllWithTimeForCallList();

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }


    public List<FormFullDTO> getAllByInternshipId(UUID internshipId) {

        MDCLog.putClassNameInMDC(SIMPLE_CLASS_NAME);
        log.info("Try to get all forms by internship id");

        List<Form> formList = formRepository.findAllByInternship(internshipId);

        log.info("Try get list of formFullDTO");

        List<FormFullDTO> formFullDTOList = mapper.map(formList);

        log.info("Successfully list of formFullDTO");

        return formFullDTOList;

    }


    public void updateStatusById(UUID formId, FormStatus status) {
        log.info("Try to get form by form id: {}", formId);

        Form one = formRepository.getOne(formId);

        log.info("Try to set status");

        one.setFormStatus(status);

        log.info("Try to update status");

        formRepository.save(one);
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

}