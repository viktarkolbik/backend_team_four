package by.exadel.internship.controller;

import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@Api(tags = "Form endpoints")
public class FormController {

    private final FormService formService;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE})
    @ApiOperation("Add new form")
    public ResponseEntity<Form> addNewForm(@RequestPart(name = "form") FormRegisterDTO form,
                                           @RequestPart(name = "file", required = false) MultipartFile file) {
        Form createdForm = formService.process(form , file);
        return new ResponseEntity<>(createdForm, HttpStatus.OK);
    }
}