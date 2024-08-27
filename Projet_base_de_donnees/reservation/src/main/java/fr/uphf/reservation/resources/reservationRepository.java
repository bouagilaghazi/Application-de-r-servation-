package fr.uphf.reservation.resources;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reservationRepository extends JpaRepository<reservation, Long>{
    List<reservation> findAllByLocataireId(Long id);
}
