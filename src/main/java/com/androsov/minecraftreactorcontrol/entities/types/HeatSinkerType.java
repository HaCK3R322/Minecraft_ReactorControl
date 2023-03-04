package com.androsov.minecraftreactorcontrol.entities.types;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Heat_sinker_type")
@AllArgsConstructor
@ToString
public class HeatSinkerType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter Integer id;
    @Getter @Setter String name;
    @Getter @Setter Integer heatCapacity;
    @Getter @Setter Integer corpusExchangeTick;
    @Getter @Setter Integer coolingTick;


    protected HeatSinkerType() {}
}

