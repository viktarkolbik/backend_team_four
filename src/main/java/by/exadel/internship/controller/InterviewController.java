package by.exadel.internship.controller;

import by.exadel.internship.annotation.UserAccessControl;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.dto.interview.InterviewInfoDTO;
import by.exadel.internship.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interviews")
@Api(tags = "Endpoints for Interview")
public class InterviewController {

    private final InterviewService interviewService;

    @UserAccessControl
    @GetMapping
    @ApiOperation("Get all Interview for User")
    public List<InterviewInfoDTO> getAllByUserId(@RequestParam UUID userId, @RequestParam UserRole userRole){
        return interviewService.getAllByUserId(userId, userRole);
    }

}
