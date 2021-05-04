package by.exadel.internship.controller;


import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;
import by.exadel.internship.dto.UserDTO;
import by.exadel.internship.service.SchedulingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
@Api(tags = "Endpoints for Scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    @AdminAccessControl
    @GetMapping("/interview-time")
    @ApiOperation("Get all users with their free time for Form")
    public List<UserDTO> getPossibleInterviewTime(@RequestParam("formId") UUID formId) {
        return schedulingService.getAdminTimeForForm(formId);
    }

}
