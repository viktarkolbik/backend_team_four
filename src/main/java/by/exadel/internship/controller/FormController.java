package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@Api(tags = "Form endpoints")
public class FormController {

    private final FormService formService;

    @PostMapping
    @ApiOperation("Add new form")
    public void addNewForm(@RequestBody FormRegisterDTO form) {
        formService.saveForm(form);
    }

    @DeleteMapping("/{formId}")
    @ApiOperation("Delete form by ID")
    public void deleteFromById(@PathVariable UUID formId){
        formService.deleteById(formId);
    }

    @PutMapping("/returnFrom/{formId}")
    @ApiOperation("do active deleted Form")
    public void doActiveDeletedForm(@PathVariable UUID formId){
        formService.doActiveDeletedFormById(formId);
    }
}