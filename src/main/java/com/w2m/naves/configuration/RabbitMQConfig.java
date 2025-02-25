package com.w2m.naves.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("spaceShipQueue", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("spaceShipExchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("spaceShipRoutingKey");
    }
}
