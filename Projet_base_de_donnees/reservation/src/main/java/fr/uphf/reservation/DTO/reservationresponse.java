package fr.uphf.reservation.DTO;
import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
public class reservationresponse {
    private Long id;
    private Long locataireId;
    private Long bienImmoId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
