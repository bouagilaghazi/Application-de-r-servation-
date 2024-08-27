package fr.uphf.bienImmo.resources;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreationBienImmoRequestODT {
    private String adresse;
    private String type;
    private int surface;
    private int nbPieces;
    private int loyer;
    private BienImmoDTO.LocataireDTO locataire;
}
