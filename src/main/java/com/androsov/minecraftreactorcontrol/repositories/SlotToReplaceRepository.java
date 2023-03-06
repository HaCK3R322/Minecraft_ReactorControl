package com.androsov.minecraftreactorcontrol.repositories;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.SlotToReplace;
import org.springframework.data.repository.CrudRepository;

public interface SlotToReplaceRepository extends CrudRepository<SlotToReplace, Integer> {
    Iterable<SlotToReplace> getAllByReactor(Reactor reactor);
}
