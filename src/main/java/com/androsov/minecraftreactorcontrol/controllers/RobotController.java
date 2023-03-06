package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.Robot;
import com.androsov.minecraftreactorcontrol.repositories.RobotRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RobotController {
    RobotRepository robotRepository;

    @PostMapping(path = "/createRobot",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createRobot(@ModelAttribute Robot robot) {
        Robot saved = robotRepository.save(robot);
        return saved.getId().toString();
    }
}
