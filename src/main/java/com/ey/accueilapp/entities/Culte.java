package com.ey.accueilapp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CULTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Culte extends PhysicalEvent {

    private int hommesIntercession;
    private int femmesIntercession;
    private int enfantsIntercession;
    private int nouveauxHommes;
    private int nouveauxFemmes;
    private int enfantsMIJ;
    private String conducteurIntercession;
    private String moderateur;

}
