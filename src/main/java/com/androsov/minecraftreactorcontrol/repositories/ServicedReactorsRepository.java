package com.androsov.minecraftreactorcontrol.repositories;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.Robot;
import com.androsov.minecraftreactorcontrol.entities.ServicedReactors;
import org.springframework.data.repository.CrudRepository;

public interface ServicedReactorsRepository extends CrudRepository<ServicedReactors, Integer> {
    Iterable<ServicedReactors> findAllByRobot(Robot robot);
    Iterable<ServicedReactors> findAllByReactor(Reactor reactor);
}
