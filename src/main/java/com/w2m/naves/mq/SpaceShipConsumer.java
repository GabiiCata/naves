package com.w2m.naves.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpaceShipConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        log.info("\u001B[1m\u001B[32m📡 MQ Recibido ->  \u001B[33mMensaje: '{}' \u001B[0m",  message);

    }
}
