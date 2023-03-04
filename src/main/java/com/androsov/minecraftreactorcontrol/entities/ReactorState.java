package com.androsov.minecraftreactorcontrol.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Reactor_state")
@AllArgsConstructor
@ToString
public class ReactorState {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    Integer id;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "reactor_id")
    Reactor reactor;
    @Getter @Setter Boolean currentlyWorking;
    @Getter @Setter Integer maxOut;
    @Getter @Setter Integer currentOut;
    @Getter @Setter Integer coreHeat;


    protected ReactorState() {}
}
