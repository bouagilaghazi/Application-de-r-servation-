package fr.uphf.reservation.resources;
import fr.uphf.reservation.DTO.reservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/reservation")
public class reservationController {
    @Autowired
    private reservationService reservationService;

    @GetMapping("/reservation")
    @ResponseStatus(HttpStatus.OK)
    public List<reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<reservation> addReservation(@RequestBody reservationRequest creationReservationRequestODT) {
        reservation reservation = reservationService.addReservation(creationReservationRequestODT);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
    @GetMapping("/reservation/{locataire_id}")
    public List<reservation> getReservationsByLocataireId(@PathVariable Long locataire_id) {
        //System.out.println("Requête reçue pour lister les réservations du locataire " + id);
        return reservationService.getReservationsByLocataireId(locataire_id);
    }

}
