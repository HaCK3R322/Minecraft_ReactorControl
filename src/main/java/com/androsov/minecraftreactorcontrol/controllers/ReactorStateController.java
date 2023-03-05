package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.ComponentProperties;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.components.FuelsSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import com.androsov.minecraftreactorcontrol.repositories.ReactorStateRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.FuelsSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.services.ReactorStateService;
import com.androsov.minecraftreactorcontrol.services.SchemesService;
import com.androsov.minecraftreactorcontrol.services.TypesService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
public class ReactorStateController {
    private static final Logger LOGGER = Logger.getLogger(ReactorStateController.class.getName());

    ReactorStateService reactorStateService;
    ReactorStateRepository reactorStateRepository;
    SchemesService schemesService;

    @PostMapping(path = "/createReactorState",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createReactorState(@ModelAttribute ReactorState reactorState) {
        ReactorState savedState = reactorStateRepository.save(reactorState);
        LOGGER.log(Level.INFO, reactorState.toString());
        return savedState.getId().toString();
    }

    @PostMapping(path = "/updateComponentProperties",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String updateFuelsSchemeComponent(@ModelAttribute ComponentProperties properties) {
        LOGGER.log(Level.INFO, "updated " + properties.getId());

        return "got it!";
    }


}
