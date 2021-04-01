package by.exadel.internship.controller;

import by.exadel.internship.dto.FormDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/form")
@Api(tags = "Form endpoints")
public class FormController {

    static private List<FormDTO> formList = new ArrayList<>();

    @PostMapping
    @ApiOperation("Add new form")
    public FormDTO addNewForm(@RequestBody FormDTO form) {
        formList.add(form);
        return form;
    }

}
