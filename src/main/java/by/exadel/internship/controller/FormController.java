package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
