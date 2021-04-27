package by.exadel.internship.controller;


import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallUserServise;
import io.swagger.annotations.Api;
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
    private final TimeForCallUserServise timeForCallUserServise;

    /*@GetMapping
    public List<TimeForCallUserDTO> makeScheduling() {
        //schedulingService.makeSchedule();
        return timeForCallUserServise.getAll();
    }*/

    @PostMapping("/save-user-time")
    public List<TimeForCallUserDTO> saveUserTime(@RequestBody List<TimeForCallUserDTO> timeForCallUserDTOList) {
        schedulingService.saveUserTime(timeForCallUserDTOList);
        return null;
    }

    @GetMapping("/make-scheduling")
    public List<TimeForCallUserDTO> makeScheduling(@RequestParam("formId") UUID formId){
        return schedulingService.getFreeTimeForForm(formId);
    }

    @PostMapping("/save-time-for-interview")
    public void saveInterviewForForm(@RequestParam(name = "formId") UUID formId,
                    @RequestBody TimeForCallUserDTO userDataTime){
        schedulingService.saveInterviewForForm(formId,userDataTime);
    }



}
