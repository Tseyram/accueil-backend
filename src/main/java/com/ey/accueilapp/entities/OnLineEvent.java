package com.ey.accueilapp.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)

public class OnLineEvent extends Event {

}
