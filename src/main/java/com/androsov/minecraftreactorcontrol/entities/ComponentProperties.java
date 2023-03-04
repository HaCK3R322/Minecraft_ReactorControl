package com.androsov.minecraftreactorcontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Component_properties")
@AllArgsConstructor
@ToString
public class ComponentProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter Integer id;
    @Getter @Setter String minecraftItemName;
    @Getter @Setter Float damage;
    @Getter @Setter Float maxDamage;
    @Getter @Setter Integer slot;


    protected ComponentProperties() {}
}
