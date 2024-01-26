package com.ey.accueilapp.entities;

import com.ey.accueilapp.enums.TypePriereEnLigne;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PRIERE ZOOM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriereZoom extends OnLineEvent {

    private TypePriereEnLigne typePriere;

}
