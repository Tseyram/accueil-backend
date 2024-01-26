package com.ey.accueilapp.dtos;

import com.ey.accueilapp.enums.TypePrierePresentiel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempsDePriereDTO extends EventDTO {
    private TypePrierePresentiel typePriere;

}
