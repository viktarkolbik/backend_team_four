package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.service.FormService;
import by.exadel.internship.service.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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

        FormFullDTO process = formService.process(form, file);
        return process;
    }

    @DeleteMapping("/{formId}")
    @ApiOperation("Delete form by ID")
    public void deleteFormById(@PathVariable UUID formId) {
        formService.deleteById(formId);
    }

    @PutMapping("/{formId}/restore")
    @ApiOperation("Restore deleted Form")
    public void restoreForm(@PathVariable UUID formId) {
        formService.restoreFormById(formId);
    }

    @GetMapping
    @ApiOperation("Get all forms")
    @ResponseStatus(HttpStatus.OK)
    public List<FormFullDTO> getAllForms() {
        return formService.getAll();
    }
}