package fr.uphf.bienImmo.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long locataireId;
    private Long bienImmoId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
