package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatExchangerType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatSinkerType;
import com.androsov.minecraftreactorcontrol.repositories.types.FuelTypeRepository;
import com.androsov.minecraftreactorcontrol.repositories.types.HeatExchangerTypeRepository;
import com.androsov.minecraftreactorcontrol.repositories.types.HeatSinkerTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TypesService {
    private FuelTypeRepository fuelTypeRepository;
    private HeatExchangerTypeRepository heatExchangerTypeRepository;
    private HeatSinkerTypeRepository heatSinkerTypeRepository;

    public FuelType addFuelType(FuelType fuelType) {
        if(fuelTypeRepository.existsByName(fuelType.getName())) {
           return fuelTypeRepository.findByName(fuelType.getName());
        } else {
            return fuelTypeRepository.save(fuelType);
        }
    }

    public HeatExchangerType addHeatExchangerType(HeatExchangerType heatExchangerType) {
        if(heatExchangerTypeRepository.existsByName(heatExchangerType.getName())) {
            return heatExchangerTypeRepository.findByName(heatExchangerType.getName());
        } else {
            return heatExchangerTypeRepository.save(heatExchangerType);
        }
    }

    public HeatSinkerType addHeatSinkerType(HeatSinkerType heatSinkerType) {
        if(heatSinkerTypeRepository.existsByName(heatSinkerType.getName())) {
            return heatSinkerTypeRepository.findByName(heatSinkerType.getName());
        } else {
            return heatSinkerTypeRepository.save(heatSinkerType);
        }
    }
}
