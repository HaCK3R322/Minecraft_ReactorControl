package com.androsov.minecraftreactorcontrol.controllers;

import com.androsov.minecraftreactorcontrol.entities.Reactor;
import com.androsov.minecraftreactorcontrol.entities.Robot;
import com.androsov.minecraftreactorcontrol.entities.ServicedReactors;
import com.androsov.minecraftreactorcontrol.entities.SlotToReplace;
import com.androsov.minecraftreactorcontrol.repositories.ReactorRepository;
import com.androsov.minecraftreactorcontrol.repositories.RobotRepository;
import com.androsov.minecraftreactorcontrol.repositories.ServicedReactorsRepository;
import com.androsov.minecraftreactorcontrol.repositories.SlotToReplaceRepository;
import com.androsov.minecraftreactorcontrol.services.ServicedReactorsService;
import jdk.jfr.MetadataDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
public class ReactorServingController {
    private final Logger LOGGER = Logger.getLogger(ReactorServingController.class.getName());

    ServicedReactorsRepository servicedReactorsRepository;
    ServicedReactorsService servicedReactorsService;
    RobotRepository robotRepository;
    SlotToReplaceRepository slotToReplaceRepository;
    ReactorRepository reactorRepository;

    /**
     * By this link, reactor can ask robot. Robot with the least number of assigned reactors will be assigned
     * @param reactor - reactor that asks for robot to serve it
     * @return can be ignored, just id of robot-reactor connection
     */
    @PostMapping(path = "/askToAssignRobot",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String assignRobot(@ModelAttribute Reactor reactor) {
        Robot robot = servicedReactorsService.getLeastBusyRobot();

        ServicedReactors servicedReactors = new ServicedReactors(null, reactor, robot);
        ServicedReactors saved = servicedReactorsRepository.save(servicedReactors);

        return saved.getRobot().getId().toString();
    }

    /**
     * By this link some reactor can ask for fuel replacement
     * @param reactor - reactor to serve
     * @param slotsToReplace - List of slots that need replacement
     * @return 0:number if no free robots assigned to this reactor, and 1 if some robot started its work
     */
    @PostMapping(path = "/askServing",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String askServing(@RequestParam("reactor") Reactor reactor, @RequestParam("slotsToReplace") List<Integer> slotsToReplace) {
        Iterable<ServicedReactors> servicedReactorsByReactor = servicedReactorsRepository.findAllByReactor(reactor);

        // indicate that this reactor needs replacing
        reactor.setNeedReplacing(true);
        reactorRepository.save(reactor);

        // assign robot to serve, if there is free robot serving this reactor
        for (ServicedReactors servicedReactors : servicedReactorsByReactor) {
            Robot assignedRobot = servicedReactors.getRobot();

            // if robot that assigned to this reactor is free rn
            if(!assignedRobot.getCurrentlyBusy()) {
                assignedRobot.setCurrentlyBusy(true); // make him busy

                for(Integer slotNumber : slotsToReplace) {
                    final SlotToReplace slotToReplace = new SlotToReplace(null, slotNumber, reactor);
                    slotToReplaceRepository.save(slotToReplace);
                }

                // tell reactor to wait until robot replace components
                return Integer.toString(1);
            }
        }

        // if there is no free robots to serve reactor,
        // then return 0 what will make reactor wait some time then re-ask for serving
        return Integer.toString(0);
    }

    /**
     * There robot can ask if some reactors he assigned to needs to be served
     * @param robot id of robot that asks
     * @return "no" - if there is no one, position of reactor if he needs
     */
    @PostMapping(path = "/isThereAJob",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String isThereAJob(@ModelAttribute Robot robot) {
        for (ServicedReactors servicedReactors : servicedReactorsRepository.findAllByRobot(robot)) {
            final Reactor reactor = servicedReactors.getReactor();

            if (reactor.getNeedReplacing()) { // if some reactors served by robot need replacing
                return reactor.getReactorPos(); // pick the first and return its id
            }
        }

        return "no"; // no job, you know...
    }

    /**
     * There, after started his serving for some reactor, he asks slots that needs to be replaced
     * @param reactorPos reactorPos of reactor with components to replace
     * @return slots, looks smthng like this: "1 2 3 4"
     */
    @PostMapping(path = "/getSlotsToReplace",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String getSlotsToReplace(@RequestParam("reactorPos") String reactorPos) {
        // This all shit is very BAD, but REQUIRED,
        // because (for some reason) the OpenComputers Internet API only accepts strings as responses
        LOGGER.log(Level.INFO, "got reactor pos: " + reactorPos);

        StringBuilder slotsStr = new StringBuilder();

        Reactor reactor = reactorRepository.getByReactorPos(reactorPos);
        LOGGER.log(Level.INFO, "reactor id of that pos: " + reactor.getId().toString());

        Iterable<SlotToReplace> slotsToReplaceForReactor = slotToReplaceRepository.getAllByReactor(reactor);

        for (SlotToReplace slotToReplace : slotsToReplaceForReactor) {
            LOGGER.log(Level.INFO, "concatenating slot " + slotToReplace.getSlot().toString());
            if (!slotsStr.toString().equals("")) {
                slotsStr.append(" ");
            }

            slotsStr.append(slotToReplace.getSlot().toString());
        }

        slotToReplaceRepository.deleteAll(slotsToReplaceForReactor); // delete, bc they already *must* be sent to replacement

        return slotsStr.toString();
    }

    /**
     * By this link robot can say that he is done this his reactor and can serve, if this will be needed
     * @param reactorPos - just served reactor position to get reactor
     * @param robot - id of robot that just served reactor
     * @return can be ignored
     */
    @PostMapping(path = "/reactorServed",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String reactorServed(@RequestParam("reactorPos") String reactorPos, @RequestParam("robot") Robot robot) {
        final Reactor reactor = reactorRepository.getByReactorPos(reactorPos);

        reactor.setNeedReplacing(false);
        reactorRepository.save(reactor);

        robot.setCurrentlyBusy(false);
        robotRepository.save(robot);

        return "No return value, and? Oh wait..";
    }

    /**
     * There reactor can ask if he can resume work, e.g. he doesn't need replacing anymore
     * @param reactor - who asked?
     * @return "yes" / "no"
     */
    @PostMapping(path = "/canIResumeWork",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String canIResumeWork(@ModelAttribute Reactor reactor) {
        Optional<Reactor> reactorOptional = reactorRepository.findById(reactor.getId());

        if(reactorOptional.isPresent()) {
            reactor = reactorOptional.get();

            if (reactor.getNeedReplacing()) { // if still need replacing
                return "no";
            } else { // if all good
                return "yes";
            }
        }

        return "no";
    }

}
