package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.repositories.ReactorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactorService {
    private ReactorRepository reactorRepository;
}
