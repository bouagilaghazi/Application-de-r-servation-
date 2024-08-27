package fr.uphf.bienImmo.resources;


import jakarta.persistence.*;
import lombok.*;




@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BienImmo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBienImmo")
    private Long idBienImmo;

    private String adresse;

    private String type;

    private int surface;

    private int nbPieces;

    private int loyer;

    @Column(name = "idLocataire")
    private Long idLocataire;

}
