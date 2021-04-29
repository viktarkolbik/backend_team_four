package by.exadel.internship.controller;


import by.exadel.internship.annotation.AdminAccessControl;
import by.exadel.internship.annotation.SuperAdminAccessControl;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallUserServise;
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

    @SuperAdminAccessControl
    @PostMapping()
    @ApiOperation("Save User free time")
    public List<TimeForCallUserDTO> saveUserTime(@RequestBody List<TimeForCallUserDTO> timeForCallUserDTOList) {
        schedulingService.saveUserTime(timeForCallUserDTOList);
    }

    @AdminAccessControl
    @GetMapping("/interview-time")
    @ApiOperation("Get all time for Form")
    public List<TimeForCallUserDTO> makeScheduling(@RequestParam("formId") UUID formId){
        return schedulingService.getFreeTimeForForm(formId);
    }

    @AdminAccessControl
    @PostMapping("/save-interview-time")
    @ApiOperation("Save interview time for Form")
    public void saveInterviewForForm(@RequestParam(name = "formId") UUID formId,
                    @RequestBody TimeForCallUserDTO userDataTime){
        schedulingService.saveInterviewForForm(formId,userDataTime);
    }



}
