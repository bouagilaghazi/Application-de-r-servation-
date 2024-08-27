package fr.uphf.bienImmo.resources;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreationBienImmoResponseODT {
    private Long idBienImmo;
    private String adresse;
    private String type;
    private int surface;
    private int nbPieces;
    private int loyer;
    private BienImmoDTO.LocataireDTO locataire;
}
