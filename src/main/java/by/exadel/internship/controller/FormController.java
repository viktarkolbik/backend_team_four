package by.exadel.internship.controller;

import by.exadel.internship.dto.FormDTO;
import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.enums.EnglishLevel;
import by.exadel.internship.dto.enums.FormStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/form")
@Api(tags = "Form endpoints")
public class FormController {

    static private FormDTO form1;
    static private FormDTO form2;
    static private FormDTO form3;
    static private List<FormDTO> formList;

    static {
        form1 = FormDTO.builder()
                .name("Jon")
                .lastName("Snow")
                .email("jonsnow@mail.com")
                .phoneNumber("+375291111111")
                .skype("jonsnow")
                .englishLevel(EnglishLevel.A2)
                .country("Belarus")
                .city("Gomel")
                .education("higher")
                .filePath("../jonsnowCV.pdf")
                .interview(new InterviewDTO())
                .formStatus(FormStatus.REGISTERED)
                .build();

        form2 = FormDTO.builder()
                .name("Arya")
                .lastName("Stark")
                .email("aryastark@mail.com")
                .phoneNumber("+375292222222")
                .skype("aryastark")
                .englishLevel(EnglishLevel.B1)
                .country("Belarus")
                .city("Gomel")
                .education("higher")
                .filePath("../aryastarkCV.pdf")
                .interview(new InterviewDTO())
                .formStatus(FormStatus.ACCEPTED)
                .build();

        form3 = FormDTO.builder()
                .name("Daenerys")
                .lastName("Targaryen")
                .email("denerystargaryen@mail.com")
                .phoneNumber("+375293333333")
                .skype("denerystargaryen")
                .englishLevel(EnglishLevel.B2)
                .country("Belarus")
                .city("Gomel")
                .education("higher")
                .filePath("../denerystargaryenCV.pdf")
                .interview(new InterviewDTO())
                .formStatus(FormStatus.REJECTED)
                .build();

        formList = new ArrayList<>();
        formList.add(form1);
        formList.add(form2);
        formList.add(form3);

    }

    @GetMapping
    @ApiOperation("Get all forms")
    public List<FormDTO> getAllForms() {
        return formList;
    }

    @GetMapping("/{formId}")
    @ApiOperation("Get form by ID")
    public FormDTO getForm(@PathVariable int formId) {
        return formList.get(formId);
    }

}
