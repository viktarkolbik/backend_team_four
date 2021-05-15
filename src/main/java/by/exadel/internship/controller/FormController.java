package by.exadel.internship.controller;

import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.annotation.UserAccessControl;
import by.exadel.internship.dto.FeedbackRequest;
import by.exadel.internship.dto.FileInfoDTO;
import by.exadel.internship.dto.enums.FormStatus;
import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.dto.interview.InterviewSaveDTO;
import by.exadel.internship.service.FormService;
import by.exadel.internship.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@Api(tags = "Endpoints for Form")
public class FormController {
    private static final String ATTACHMENT = "attachment;filename= ";

    private final FormService formService;
    private final InterviewService interviewService;


    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE})
    @ApiOperation("Add new form")
    @ResponseStatus(HttpStatus.CREATED)
    public FormFullDTO addNewForm(@RequestPart(name = "form") FormRegisterDTO form,
                                  @RequestPart(name = "file", required = false) MultipartFile file) {
        return formService.process(form, file);
    }

    @UserAccessControl
    @GetMapping
    @ApiOperation("Get all forms by internship id or user id")
    public List<FormFullDTO> getAllFormsById(@RequestParam(value = "internshipId", required = false) UUID internshipId
            , @RequestParam(value = "userId", required = false) UUID userId) {

        return formService.getAllByCondition(internshipId, userId);

    }

    @AdminAccessControl
    @GetMapping("/{formId}")
    @ApiOperation("Get form by id")
    public FormFullDTO getFormById(@PathVariable("formId") UUID formId){
        return formService.getById(formId);
    }


    @AdminAccessControl
    @GetMapping("/{formId}/file")
    @ApiOperation("Get file by form")
    public ResponseEntity<Resource> getFileByForm(@PathVariable("formId") UUID formId) {
        FileInfoDTO fileInfoDTO = formService.getFileByFormId(formId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,ATTACHMENT + fileInfoDTO.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileInfoDTO.getResource().contentLength())
                .body(fileInfoDTO.getResource());
    }

    @SuperAdminAccessControl
    @DeleteMapping("/{formId}")
    @ApiOperation("Delete form by ID")
    public void deleteFormById(@PathVariable UUID formId) {
        formService.deleteById(formId);
    }

    @SuperAdminAccessControl
    @PutMapping("/{formId}/restore")
    @ApiOperation("Restore deleted Form")
    public void restoreForm(@PathVariable UUID formId) {
        formService.restoreFormById(formId);
    }

    @UserAccessControl
    @PutMapping("/{formId}/feedback")
    @ApiOperation("Save feedback")
    public void saveFeedback(@PathVariable UUID formId, @RequestBody FeedbackRequest feedbackRequest) {
        formService.updateFeedback(formId, feedbackRequest);
    }


    @PutMapping("/updateStatus")
    @ApiOperation("Update form status")
    @ResponseStatus(HttpStatus.OK)
    @AdminAccessControl
    public void updateFormStatus(@RequestParam("formId") UUID formId, @RequestParam("status") FormStatus status) {
        formService.updateStatusById(formId, status);
    }


    @AdminAccessControl
    @PostMapping("/{formId}/interviews")
    @ApiOperation("Save interview time for Form")
    public void saveInterviewForForm(@PathVariable(name = "formId") UUID formId,
                                     @RequestBody InterviewSaveDTO interviewDTO) {
        interviewService.saveInterviewForForm(formId, interviewDTO);
    }

    @AdminAccessControl
    @PutMapping("/{formId}/interviews/{interviewId}")
    @ApiOperation("Rewrite interview time for Form")
    public void rewriteInterviewForForm(@PathVariable(name = "formId") UUID formId,
                                        @PathVariable(name = "interviewId") UUID interviewId,
                                        @RequestBody InterviewSaveDTO interviewDTO) {
        interviewService.rewriteInterviewTime(formId, interviewDTO);
    }
}