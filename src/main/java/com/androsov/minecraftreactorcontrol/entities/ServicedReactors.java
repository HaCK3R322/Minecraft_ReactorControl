package com.androsov.minecraftreactorcontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Serviced_reactors")
@AllArgsConstructor
@ToString
public class ServicedReactors {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    Integer id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "reactor_id")
    Reactor reactor;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "robot_id")
    Robot robot;

    public ServicedReactors() {}
}
