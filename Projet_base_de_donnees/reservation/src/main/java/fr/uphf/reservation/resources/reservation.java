package fr.uphf.reservation.resources;



import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long locataireId;

    private Long bienImmoId;

    private LocalDate dateDebut;

    private LocalDate dateFin;
    private  int loyer;
}