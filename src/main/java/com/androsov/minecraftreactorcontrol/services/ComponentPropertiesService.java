package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.repositories.ComponentPropertiesRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComponentPropertiesService {
    private ComponentPropertiesRepository repository;
}
