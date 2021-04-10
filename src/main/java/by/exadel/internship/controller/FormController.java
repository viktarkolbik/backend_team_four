package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@Api(tags = "Endpoints for Form")
public class FormController {

    private final FormService formService;

    @PostMapping
    @ApiOperation("Add new form")
    public void addNewForm(@RequestBody FormRegisterDTO form) {
        formService.saveForm(form);
    }

    @GetMapping
    @ApiOperation("Get all forms")
    public List<Form> getAllForms() {
        return formService.getAll();
    }
}