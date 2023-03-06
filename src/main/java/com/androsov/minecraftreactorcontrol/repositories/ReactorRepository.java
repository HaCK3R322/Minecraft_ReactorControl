package com.androsov.minecraftreactorcontrol.repositories;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import org.springframework.data.repository.CrudRepository;

public interface ReactorRepository extends CrudRepository<Reactor, Integer> {
    Reactor getByReactorPos(String reactorPos);
}
