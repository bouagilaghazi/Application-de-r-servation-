package fr.uphf.Locataire.DTO;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocataireResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse ;
    private String numTel;
}
