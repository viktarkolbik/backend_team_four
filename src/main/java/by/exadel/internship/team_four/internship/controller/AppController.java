package by.exadel.internship.team_four.internship.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Test")
public class AppController {

    @GetMapping("/hello")
    @ApiOperation("Test Get Method!!!")
    public String greetingGet() {
        return "Hello World from GetMethod!";
    }

    @PostMapping("/hello")
    @ApiOperation("Test Post Method!!!")
    public String greetingPost() {
        return "Hello World from PostMethod!";
    }

    @PutMapping("/hello")
    @ApiOperation("Test Put Method!!!")
    public String greetingPut() {
        return "Hello World from PutMethod!";
    }

}
