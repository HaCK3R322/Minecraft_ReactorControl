package com.androsov.minecraftreactorcontrol.repositories.types;

import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatExchangerType;
import org.springframework.data.repository.CrudRepository;

public interface HeatExchangerTypeRepository extends CrudRepository<HeatExchangerType, Integer> {
    Boolean existsByName(String name);
    HeatExchangerType findByName(String name);
}
