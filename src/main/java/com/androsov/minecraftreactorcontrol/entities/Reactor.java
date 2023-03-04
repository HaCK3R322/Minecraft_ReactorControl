package com.androsov.minecraftreactorcontrol.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Reactor")
@AllArgsConstructor
@ToString
public class Reactor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    Integer id;
    @Getter @Setter String reactorPos;


    protected Reactor() {}
}
