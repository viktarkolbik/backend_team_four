package by.exadel.internship.controller;


import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.dto.timeForCall.UserTimeSlotWithUserIdDTO;
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

    @AdminAccessControl
    @PostMapping("/save-interview-time")
    @ApiOperation("Save interview time for Form")
    public void saveInterviewForForm(@RequestParam(name = "formId") UUID formId,
                                     @RequestBody UserTimeSlotWithUserIdDTO time) {
        schedulingService.saveInterviewForForm(formId, time);
    }

    @AdminAccessControl
    @PutMapping("/save-interview-time")
    @ApiOperation("Rewrite interview time for Form")
    public void rewriteInterviewForForm(@RequestParam(name = "formId") UUID formId,
                                        @RequestBody UserTimeSlotWithUserIdDTO time) {
        schedulingService.rewriteInterviewTime(formId,time);
    }


}
