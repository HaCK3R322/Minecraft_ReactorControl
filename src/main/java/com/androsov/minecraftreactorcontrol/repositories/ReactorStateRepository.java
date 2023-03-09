package com.androsov.minecraftreactorcontrol.repositories;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import org.springframework.data.repository.CrudRepository;

public interface ReactorStateRepository extends CrudRepository<ReactorState, Integer> {
    ReactorState findByReactor(Reactor reactor);
}
