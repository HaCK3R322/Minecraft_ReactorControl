package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.ComponentProperties;
import com.androsov.minecraftreactorcontrol.entities.components.FuelsSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.components.HeatExchangersSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.components.HeatSinkersSchemeComponent;
import com.androsov.minecraftreactorcontrol.repositories.components.FuelsSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatExchangersSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatSinkersSchemeComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SchemesController {
    FuelsSchemeComponentRepository fuelsSchemeComponentRepository;
    HeatExchangersSchemeComponentRepository heatExchangersSchemeComponentRepository;
    HeatSinkersSchemeComponentRepository heatSinkersSchemeComponentRepository;

    @PostMapping(path = "/createFuelsSchemeComponent",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createFuelsSchemeComponent(@ModelAttribute FuelsSchemeComponent component) {
        FuelsSchemeComponent savedType = fuelsSchemeComponentRepository.save(component);

        return savedType.getId().toString();
    }

    @PostMapping(path = "/createHeatExchangersSchemeComponent",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createHeatExchangersSchemeComponent(@ModelAttribute HeatExchangersSchemeComponent component) {
        HeatExchangersSchemeComponent savedType = heatExchangersSchemeComponentRepository.save(component);

        return savedType.getId().toString();
    }

    @PostMapping(path = "/createHeatSinkersSchemeComponent",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createHeatSinkersSchemeComponent(@ModelAttribute HeatSinkersSchemeComponent component) {
        HeatSinkersSchemeComponent savedType = heatSinkersSchemeComponentRepository.save(component);

        return savedType.getId().toString();
    }
}

