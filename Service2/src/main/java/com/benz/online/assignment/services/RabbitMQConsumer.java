package com.benz.online.assignment.services;

import com.benz.online.assignment.domain.StoreDataDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Read data from RABBIT MQ
 */
@Component
public class RabbitMQConsumer {
    @Autowired
    StoreOrUpdateDataService storeOrUpdateDataService;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @RabbitListener(queues = "${store.rabbitmq.queue}")
    public void recievedMessage(byte[]  storeDataDAO) throws Exception {
        logger.info("Recieved Message From RabbitMQ: " + storeDataDAO);
        storeOrUpdateDataService.storeOrUpdateData(storeDataDAO);

    }
}
