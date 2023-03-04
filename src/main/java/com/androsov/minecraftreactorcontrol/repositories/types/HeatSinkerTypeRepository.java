package com.androsov.minecraftreactorcontrol.repositories.types;

import com.androsov.minecraftreactorcontrol.entities.types.HeatExchangerType;
import com.androsov.minecraftreactorcontrol.entities.types.HeatSinkerType;
import org.springframework.data.repository.CrudRepository;

public interface HeatSinkerTypeRepository extends CrudRepository<HeatSinkerType, Integer> {
    Boolean existsByName(String name);
    HeatSinkerType findByName(String name);
}
