package fr.uphf.reservation.resources;

import org.springframework.amqp.rabbit.annotation.RabbitListener ;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @RabbitListener( queues = "bienImmoQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
