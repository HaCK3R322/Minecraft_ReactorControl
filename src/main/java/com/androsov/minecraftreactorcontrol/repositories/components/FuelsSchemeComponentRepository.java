package com.androsov.minecraftreactorcontrol.repositories.components;

import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.components.FuelsSchemeComponent;
import org.springframework.data.repository.CrudRepository;

public interface FuelsSchemeComponentRepository extends CrudRepository<FuelsSchemeComponent, Integer> {
    Iterable<FuelsSchemeComponent> findAllByReactorState(ReactorState reactorState);
}
