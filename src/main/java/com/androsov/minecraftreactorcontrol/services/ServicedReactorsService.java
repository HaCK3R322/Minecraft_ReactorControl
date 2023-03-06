package com.androsov.minecraftreactorcontrol.services;

import com.androsov.minecraftreactorcontrol.entities.Robot;
import com.androsov.minecraftreactorcontrol.entities.ServicedReactors;
import com.androsov.minecraftreactorcontrol.repositories.RobotRepository;
import com.androsov.minecraftreactorcontrol.repositories.ServicedReactorsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ServicedReactorsService {

    ServicedReactorsRepository servicedReactorsRepository;

    RobotRepository robotRepository;

    public Robot getLeastBusyRobot() {
        final int numberOfAvailableRobots = ((Collection<Robot>) robotRepository.findAll()).size();

        if (numberOfAvailableRobots < 1) {
            return null;
        }

        int min = Integer.MAX_VALUE;
        Robot leastBusyRobot = null;
        for (Robot robot : robotRepository.findAll()) {
            // get the number of served reactors by this robot
            final Collection<ServicedReactors> servicedReactorsForThisRobot =
                    (Collection<ServicedReactors>) servicedReactorsRepository.findAllByRobot(robot);
            final int numberOfServedReactors = servicedReactorsForThisRobot.size();

            if (numberOfServedReactors <= min) {
                min = numberOfServedReactors;
                leastBusyRobot = robot;
            }
        }

        return leastBusyRobot;
    }
}
