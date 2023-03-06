package com.androsov.minecraftreactorcontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Robot")
@AllArgsConstructor
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    Integer id;

    @Getter @Setter
    String name;

    @Getter @Setter
    Boolean currentlyBusy;

    public Robot() {}
}
