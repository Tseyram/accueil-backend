package com.ey.accueilapp.dtos;

import java.time.LocalDate;

import com.ey.accueilapp.enums.TypePriereEnLigne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriereZoomDTO extends OnLineEventDTO {

    private LocalDate date;

    private TypePriereEnLigne typePriere;

}
