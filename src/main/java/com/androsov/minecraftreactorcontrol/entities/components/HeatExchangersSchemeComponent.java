package com.androsov.minecraftreactorcontrol.entities.components;

import javax.persistence.*;

import com.androsov.minecraftreactorcontrol.entities.ComponentProperties;
import com.androsov.minecraftreactorcontrol.entities.ReactorState;
import com.androsov.minecraftreactorcontrol.entities.types.HeatExchangerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Heat_exchangers_scheme")
@AllArgsConstructor
@ToString
public class HeatExchangersSchemeComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    Integer id;
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "reactor_state_id")
    ReactorState reactorState;
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "component_properties_id")
    ComponentProperties componentProperties;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "heat_exchanger_type_id")
    HeatExchangerType heatExchangerType;


    protected HeatExchangersSchemeComponent() {}
}