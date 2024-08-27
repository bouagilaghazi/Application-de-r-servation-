package fr.uphf.Locataire.DTO;
import lombok.* ;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class reservationDTO {
    private Long id;
    private Long locataireId;
    private Long bienImmoId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int loyer;
}
