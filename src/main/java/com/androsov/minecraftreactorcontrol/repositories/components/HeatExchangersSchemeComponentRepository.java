package com.androsov.minecraftreactorcontrol.repositories.components;

import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.components.HeatExchangersSchemeComponent;
import org.springframework.data.repository.CrudRepository;

public interface HeatExchangersSchemeComponentRepository extends CrudRepository<HeatExchangersSchemeComponent, Integer> {
    Iterable<HeatExchangersSchemeComponent> findAllByReactorState(ReactorState reactorState);
}
