package com.ey.accueilapp.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ey.accueilapp.enums.TypeEvent;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String conducteur;
    @Temporal(TemporalType.TIME)
    private LocalTime debut;
    @Temporal(TemporalType.TIME)
    private LocalTime fin;
    private String titre;
    private String name;
    private int nombreParticipants;
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
}
