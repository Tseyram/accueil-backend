package com.ey.accueilapp.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ey.accueilapp.enums.TypeEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private LocalDate date;
    private String conducteur;
    private LocalTime debut;
    private LocalTime fin;
    private String titre;
    private String name;
    private int nombreParticipants;
    private int nombreAdultes;
    private TypeEvent typeEvent;
}
