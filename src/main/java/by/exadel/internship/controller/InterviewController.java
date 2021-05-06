package by.exadel.internship.controller;

import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interviews")
@Api(tags = "Endpoints for Interview")
public class InterviewController {

    private final InterviewService interviewService;

    @AdminAccessControl
    @GetMapping
    @ApiOperation("Get all Interview for User")
    public List<InterviewDTO> getAllByUserId(@RequestParam UUID userId, @RequestParam UserRole userRole){
        return interviewService.getAllByUserId(userId, userRole);
    }

    @AdminAccessControl
    @PostMapping("/{formId}/save-interview-time")
    @ApiOperation("Save interview time for Form")
    public void saveInterviewForForm(@PathVariable(name = "formId") UUID formId,
                                     @RequestBody InterviewDTO interviewDTO) {
        interviewService.saveInterviewForForm(formId, interviewDTO);
    }

    @AdminAccessControl
    @PutMapping("/{formId}/save-interview-time")
    @ApiOperation("Rewrite interview time for Form")
    public void rewriteInterviewForForm(@PathVariable(name = "formId") UUID formId,
                                        @RequestBody InterviewDTO interviewDTO) {
        interviewService.rewriteInterviewTime(formId,interviewDTO);
    }

}
