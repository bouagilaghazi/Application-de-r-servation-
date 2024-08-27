package fr.uphf.Locataire.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BienDTO {
    @JsonProperty("idBienImmo")
    private Long idBienImmo;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("type")
    private String type;

    @JsonProperty("surface")
    private int surface;

    @JsonProperty("nbPieces")
    private int nbPieces;

    @JsonProperty("loyer")
    private int loyer;

    @JsonProperty("locataire")
    private LocataireDTO locataire;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocataireDTO {
        @JsonProperty("idLocataire")
        Long idLocataire;
    }

}