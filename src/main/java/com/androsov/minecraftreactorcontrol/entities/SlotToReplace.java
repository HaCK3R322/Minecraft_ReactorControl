package com.androsov.minecraftreactorcontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Slots_to_replace")
@AllArgsConstructor
public class SlotToReplace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    Integer id;

    @Getter @Setter
    Integer slot;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "reactor_id")
    Reactor reactor;

    public SlotToReplace() {

    }
}
