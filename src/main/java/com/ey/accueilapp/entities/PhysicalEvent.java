package com.ey.accueilapp.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhysicalEvent extends Event {

    private int nombreHommes;
    private int nombreFemmes;
    private int nombreEnfants;
    private int connexions;

}
