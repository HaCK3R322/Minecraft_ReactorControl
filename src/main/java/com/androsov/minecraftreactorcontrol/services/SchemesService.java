package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.repositories.components.FuelsSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatExchangersSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatSinkersSchemeComponentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SchemesService {
    private FuelsSchemeComponentRepository fuelsRepository;
    private HeatExchangersSchemeComponentRepository heatExchangersRepository;
    private HeatSinkersSchemeComponentRepository heatSinkersRepository;
}
