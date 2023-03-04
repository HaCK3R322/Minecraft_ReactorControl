package com.androsov.minecraftreactorcontrol.repositories.types;

import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface FuelTypeRepository extends CrudRepository<FuelType, Integer> {
    Boolean existsByName(String name);
    FuelType findByName(String name);
}
