package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.components.FuelsSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.components.HeatExchangersSchemeComponent;
import com.androsov.minecraftreactorcontrol.entities.components.HeatSinkersSchemeComponent;
import com.androsov.minecraftreactorcontrol.repositories.ReactorRepository;
import com.androsov.minecraftreactorcontrol.repositories.ReactorStateRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.FuelsSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatExchangersSchemeComponentRepository;
import com.androsov.minecraftreactorcontrol.repositories.components.HeatSinkersSchemeComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.State;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@CrossOrigin
public class StateRepresentationController {
    private final Logger LOGGER = Logger.getLogger(StateRepresentationController.class.getName());

    ReactorRepository reactorRepository;
    ReactorStateRepository reactorStateRepository;
    FuelsSchemeComponentRepository fuelsSchemeComponentRepository;
    HeatExchangersSchemeComponentRepository exchangersSchemeComponentRepository;
    HeatSinkersSchemeComponentRepository sinkersSchemeComponentRepository;

    @GetMapping("/getAllAvailableReactors")
    public Iterable<Reactor> getAllAvailableReactors() {
        return reactorRepository.findAll();
    }

    @PostMapping(path = "/getReactorFullState",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String getState(@RequestBody Reactor reactor) {
        final ReactorState reactorState = reactorStateRepository.findByReactor(reactor);

        return new StateSchemes(
                fuelsSchemeComponentRepository.findAllByReactorState(reactorState),
                exchangersSchemeComponentRepository.findAllByReactorState(reactorState),
                sinkersSchemeComponentRepository.findAllByReactorState(reactorState)
        ).toJson();
    }

    @PostMapping(path = "/getReactorFuelState",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFuelState(@RequestBody Reactor reactor) {
        final ReactorState reactorState = reactorStateRepository.findByReactor(reactor);

        final Iterable<FuelsSchemeComponent> fuels = fuelsSchemeComponentRepository.findAllByReactorState(reactorState);

        return StateSchemes.getJsonFuels(fuels);
    }

    @AllArgsConstructor
    private static class StateSchemes {
        public Iterable<FuelsSchemeComponent> fuels;
        public Iterable<HeatExchangersSchemeComponent> exchangers;
        public Iterable<HeatSinkersSchemeComponent> sinkers;

        public String toJson() {
            StringBuilder sb = new StringBuilder();

            sb.append("{ \"fuels\": [");

            Iterator<FuelsSchemeComponent> fiterator = fuels.iterator();
            while (fiterator.hasNext()) {
                FuelsSchemeComponent fuel = fiterator.next();
                sb.append(getJsonFuel(fuel));
                if (fiterator.hasNext()) {
                    sb.append(",");
                }
            }

            sb.append("], \"exchangers\": [");

            Iterator<HeatExchangersSchemeComponent> eiterator = exchangers.iterator();
            while (eiterator.hasNext()) {
                HeatExchangersSchemeComponent exchanger = eiterator.next();
                sb.append(getJsonExchanger(exchanger));
                if (eiterator.hasNext()) {
                    sb.append(",");
                }
            }

            sb.append("], \"sinkers\": [");

            Iterator<HeatSinkersSchemeComponent> siterator = sinkers.iterator();
            while (siterator.hasNext()) {
                HeatSinkersSchemeComponent sinker = siterator.next();
                sb.append(getJsonSinker(sinker));
                if (siterator.hasNext()) {
                    sb.append(",");
                }
            }

            sb.append("]}");

            return sb.toString();
        }

        public static String getJsonFuels(Iterable<FuelsSchemeComponent> fuels) {
            StringBuilder sb = new StringBuilder();

            sb.append("{ \"fuels\": [");

            Iterator<FuelsSchemeComponent> fiterator = fuels.iterator();
            while (fiterator.hasNext()) {
                FuelsSchemeComponent fuel = fiterator.next();
                sb.append(getJsonFuel(fuel));
                if (fiterator.hasNext()) {
                    sb.append(",");
                }
            }

            sb.append("]}");

            return sb.toString();
        }

        public static String getJsonFuel(FuelsSchemeComponent fuel) {
            String json = "{";

            json += "\"name\": \"" + fuel.getComponentProperties().getMinecraftItemName() + "\"";
            json += ",\"slot\":" + fuel.getComponentProperties().getSlot();
            json += ",\"damage\":" + fuel.getComponentProperties().getDamage();
            json += ",\"maxDamage\":" + fuel.getComponentProperties().getMaxDamage();

            json += "}";

            return json;
        }

        private static String getJsonExchanger(HeatExchangersSchemeComponent exchanger) {
            String json = "{";

            json += "\"name\": \"" + exchanger.getComponentProperties().getMinecraftItemName()  + "\",";
            json += "\"slot\":" + exchanger.getComponentProperties().getSlot();

            json += "}";

            return json;
        }

        private  static String getJsonSinker(HeatSinkersSchemeComponent sinker) {
            String json = "{";

            json += "\"name\": \"" + sinker.getComponentProperties().getMinecraftItemName()  + "\",";
            json += "\"slot\":" + sinker.getComponentProperties().getSlot();

            json += "}";

            return json;
        }
    }
}
