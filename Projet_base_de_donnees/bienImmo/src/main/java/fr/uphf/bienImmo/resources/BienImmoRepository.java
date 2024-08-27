package fr.uphf.bienImmo.resources;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BienImmoRepository extends JpaRepository<BienImmo, Long> {
    //méthode qui retourne une liste de BienImmo
    List<BienImmo> findByIdLocataire(Long locataireId);
}
