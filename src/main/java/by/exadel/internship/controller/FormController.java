package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
@Api(tags = "Form endpoints")
public class FormController {

    @Autowired
    public FormService formService;

    @PostMapping
    @ApiOperation("Add new form")
    public void addNewForm(@RequestBody FormRegisterDTO form) {
        formService.saveForm(form);
    }
}