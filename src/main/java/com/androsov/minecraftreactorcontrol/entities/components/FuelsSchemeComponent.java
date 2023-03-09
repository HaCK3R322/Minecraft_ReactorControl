package com.androsov.minecraftreactorcontrol.entities.components;

import javax.persistence.*;

import com.androsov.minecraftreactorcontrol.entities.ComponentProperties;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.types.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Fuels_scheme")
@AllArgsConstructor
@ToString
public class FuelsSchemeComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter Integer id;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "reactor_state_id")
    ReactorState reactorState;
    @OneToOne
    @JoinColumn(name = "component_properties_id")
    @Getter @Setter ComponentProperties componentProperties;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    FuelType fuelType;


    protected FuelsSchemeComponent() {}
}
