package com.w2m.naves.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpaceShipProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public SpaceShipProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async // async para separar el mq de la operacion
    public void sendSpaceShipMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("\u001B[1m\u001B[32m📡 MQ Enviado -> \u001B[34mExchange: {}, \u001B[35mRoutingKey: {}, \u001B[33mMensaje: '{}' \u001B[0m", exchange, routingKey, message);

    }
}
