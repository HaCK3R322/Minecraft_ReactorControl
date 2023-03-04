package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.repositories.ReactorRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReactorController {
    ReactorRepository reactorRepository;

    @PostMapping(path = "/createReactor",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createReactor(@ModelAttribute Reactor reactor) {
        Reactor saved = reactorRepository.save(reactor);
        return saved.getId().toString();
    }
}
