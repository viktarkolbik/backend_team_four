package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@Api(tags = "Endpoints for Form")
public class FormController {

    private final FormService formService;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE})
    @ApiOperation("Add new form")
    @ResponseStatus(HttpStatus.CREATED)
    public FormFullDTO addNewForm(@RequestPart(name = "form") FormRegisterDTO form,
                                  @RequestPart(name = "file", required = false) MultipartFile file) {
        return formService.process(form, file);
    }

    @GetMapping
    @ApiOperation("Get all forms")
    @ResponseStatus(HttpStatus.OK)
    public List<Form> getAllForms() {
        return formService.getAll();
    }
}