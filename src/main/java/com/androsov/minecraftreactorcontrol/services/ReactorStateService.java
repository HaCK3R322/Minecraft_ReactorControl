package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.repositories.ReactorStateRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactorStateService {
    private ReactorStateRepository repository;
}
