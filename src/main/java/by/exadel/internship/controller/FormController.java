package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/forms")
@Api(tags = "Form endpoints")
public class FormController {

    @Autowired
    public FormService formService;

    static private List<FormRegisterDTO> formList = new ArrayList<>();

    @PostMapping
    @ApiOperation("Add new form")
    public FormRegisterDTO addNewForm(@RequestBody FormRegisterDTO form) {
        formList.add(form);
        return form;
    }

}
