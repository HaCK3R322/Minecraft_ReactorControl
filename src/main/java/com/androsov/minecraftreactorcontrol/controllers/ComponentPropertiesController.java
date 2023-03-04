package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.ComponentProperties;
import com.androsov.minecraftreactorcontrol.repositories.ComponentPropertiesRepository;
import com.androsov.minecraftreactorcontrol.services.ComponentPropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ComponentPropertiesController {
    ComponentPropertiesService componentPropertiesService;
    ComponentPropertiesRepository componentPropertiesRepository;

    @PostMapping(path = "/createComponentProperties",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createHeatExchangerType(@ModelAttribute ComponentProperties type) {
        ComponentProperties savedType = componentPropertiesRepository.save(type);

        return savedType.getId().toString();
    }
}
