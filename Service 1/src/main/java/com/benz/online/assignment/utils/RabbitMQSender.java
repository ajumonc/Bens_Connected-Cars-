package com.benz.online.assignment.utils;

import com.benz.online.assignment.model.StoreDataDAO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Rabbit MQ sender
 */
@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${store.rabbitmq.exchange}")
    private String exchange;

    @Value("${store.rabbitmq.routingkey}")
    private String routingkey;

    public void send(byte[] company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }
}
