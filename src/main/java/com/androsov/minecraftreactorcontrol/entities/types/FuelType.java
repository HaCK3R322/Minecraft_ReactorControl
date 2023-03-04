package com.androsov.minecraftreactorcontrol.entities.types;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Fuel_type")
@AllArgsConstructor
@ToString
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter Integer id;
    @Getter @Setter String name;
    @Getter @Setter Integer timeToDeplet;
    @Getter @Setter Integer energyTick;
    @Getter @Setter Integer heatTick;


    protected FuelType() {}
}
