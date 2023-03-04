package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatExchangerType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatSinkerType;
import com.androsov.minecraftreactorcontrol.services.TypesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
public class TypesController {
    private static final Logger LOGGER = Logger.getLogger(TypesController.class.getName());
    TypesService typesService;

    @PostMapping(path = "/createFuelType",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createFuelType(@ModelAttribute FuelType fuelType) {
        FuelType savedFuelType = typesService.addFuelType(fuelType);
        LOGGER.log(Level.INFO, savedFuelType.getId().toString());
        return savedFuelType.getId().toString();
    }

    @PostMapping(path = "/createHeatExchangerType",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createHeatExchangerType(@ModelAttribute HeatExchangerType type) {
        HeatExchangerType savedType = typesService.addHeatExchangerType(type);

        return savedType.getId().toString();
    }

    @PostMapping(path = "/createHeatSinkerType",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createHeatSinkerType(@ModelAttribute HeatSinkerType type) {
        HeatSinkerType savedType = typesService.addHeatSinkerType(type);

        return savedType.getId().toString();
    }
}
