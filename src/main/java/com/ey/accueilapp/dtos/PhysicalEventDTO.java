package com.ey.accueilapp.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalEventDTO extends EventDTO {
    private int nombreHommes;
    private int nombreFemmes;
    private int nombreEnfants;
    private int connexions;

}
