package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/forms")
@Api(tags = "Form endpoints")
public class FormController {

    private final FormService formService;

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("Add new form")
    public ResponseEntity<Form> addNewForm(@RequestPart(name = "form") FormRegisterDTO form,
                                           @RequestPart(name = "file", required = false) MultipartFile file) {
        formService.uploadFile(file);
        Form createdForm = formService.saveForm(form);
        return new ResponseEntity<>(createdForm, HttpStatus.OK);
    }


}