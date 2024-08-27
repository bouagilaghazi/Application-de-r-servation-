package fr.uphf.bienImmo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, Object message) {
        logger.info("Sending message to exchange: {}, with routingKey: {}", exchange, routingKey);
        logger.info("Message: {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}