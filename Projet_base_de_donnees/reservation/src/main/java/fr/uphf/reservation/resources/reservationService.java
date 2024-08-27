package fr.uphf.reservation.resources;
import fr.uphf.reservation.DTO.config.BienDTO;
import fr.uphf.reservation.DTO.config.RabbitMQConfig;
import fr.uphf.reservation.DTO.reservationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class reservationService {

    @Autowired
    private reservationRepository reservationRepository;

    public List<reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public reservation addReservation(reservationRequest ReservationRequest) {
        reservation reservation = new reservation();
        reservation.setLocataireId(ReservationRequest.getLocataireId());
        reservation.setBienImmoId(ReservationRequest.getBienImmoId());
        reservation.setDateDebut(ReservationRequest.getDateDebut());
        reservation.setDateFin(ReservationRequest.getDateFin());
        reservation.setLoyer(ReservationRequest.getLoyer());
        return reservationRepository.save(reservation);

    }

    public List<reservation> getReservationsByLocataireId(Long id) {
        return reservationRepository.findAllByLocataireId(id);
    }


    //Configuration en tant que Consumer RabbitMQ pour recevoir les messages provenant du microservice BienImmo
    @RabbitListener(queues = "#{T(fr.uphf.reservation.DTO.config.RabbitMQConfig).QUEUE}")
    public void receiveBienImmo(BienDTO bienImmo) {
        System.out.println("Received msg = " + bienImmo);

    }

}
