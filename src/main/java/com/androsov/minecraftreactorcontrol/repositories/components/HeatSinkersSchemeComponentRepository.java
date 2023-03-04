package com.androsov.minecraftreactorcontrol.repositories.components;

import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.components.HeatExchangersSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.components.HeatSinkersSchemeComponent;
import org.springframework.data.repository.CrudRepository;

public interface HeatSinkersSchemeComponentRepository extends CrudRepository<HeatSinkersSchemeComponent, Integer> {
    Iterable<HeatSinkersSchemeComponent> findAllByReactorState(ReactorState reactorState);
}
