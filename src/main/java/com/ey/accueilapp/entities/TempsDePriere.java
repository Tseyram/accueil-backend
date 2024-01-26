package com.ey.accueilapp.entities;

import com.ey.accueilapp.enums.TypePrierePresentiel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PRIERE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempsDePriere extends PhysicalEvent {
    private TypePrierePresentiel typePriere;

}
