package by.exadel.internship.controller;

import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.interview.InterviewWithUserNameDTO;
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
    public List<InterviewWithUserNameDTO> getAllByUserId(@RequestParam UUID userId, @RequestParam UserRole userRole){
        return interviewService.getAllByUserId(userId, userRole);
    }

}
