package by.exadel.internship.controller;


import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.entity.TimeForCallUser;
import by.exadel.internship.service.SchedulingService;
import by.exadel.internship.service.TimeForCallService;
import by.exadel.internship.service.TimeForCallUserServise;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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
        return schedulingService.makeSchedule(formId);
    }

    @PostMapping("/save-time-for-interview")
    public void saveInterviewForForm(@RequestParam(name = "formId") UUID formId,
                    @RequestBody TimeForCallUserDTO userDataTime){
        schedulingService.saveInterviewForForm(formId,userDataTime);
    }



}
