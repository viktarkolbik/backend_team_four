package by.exadel.internship.controller;


import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallService;
import by.exadel.internship.service.TimeForCallUserServise;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
@Api(tags = "Endpoints for Scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;
    private final TimeForCallUserServise timeForCallUserServise;

    @GetMapping
    public List<TimeForCallUserDTO> makeScheduling() {
        //schedulingService.makeSchedule();
        return timeForCallUserServise.getAll();
    }

    @PostMapping
    public List<TimeForCallUserDTO> saveUserTime(@RequestBody List<TimeForCallUserDTO> timeForCallUserDTOList) {
        schedulingService.saveUserTime(timeForCallUserDTOList);
        return null;
    }

}
