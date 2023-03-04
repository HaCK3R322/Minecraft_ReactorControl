package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.entities.components.FuelsSchemeComponent;
import com.androsov.minecraftreactorcontrol.repositories.components.FuelsSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatExchangersSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatSinkersSchemeComponentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@AllArgsConstructor
public class SchemesService {
    private FuelsSchemeComponentRepository fuelsRepository;
    private HeatExchangersSchemeComponentRepository heatExchangersRepository;
    private HeatSinkersSchemeComponentRepository heatSinkersRepository;

    private static Logger LOGGER = Logger.getLogger(SchemesService.class.getName());
    public String updateFuelsScheme(Iterable<FuelsSchemeComponent> components) {
        for (FuelsSchemeComponent component:
             components) {
            LOGGER.log(Level.INFO, component.toString());
        }

        return "Got!";
    }
}
