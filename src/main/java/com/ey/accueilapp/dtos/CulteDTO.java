package com.ey.accueilapp.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CulteDTO extends PhysicalEventDTO {
    private int hommesIntercession;
    private int femmesIntercession;
    private int enfantsIntercession;
    private int nouveauxHommes;
    private int nouveauxFemmes;
    private int enfantsMIJ;
    private String conducteurIntercession;
    private String moderatrice;

}
