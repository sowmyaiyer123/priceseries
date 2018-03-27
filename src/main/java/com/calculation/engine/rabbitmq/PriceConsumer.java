package com.calculation.engine.rabbitmq;

import com.calculation.engine.service.DataStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PriceConsumer.class);
    @Autowired
    DataStoreService dataStoreService;

    @RabbitListener(queues = {SpringAmqpConfig.queueName})
    public void receiveMessage(Double price) {
        dataStoreService.storePrice(price);
        logger.info("Received price: " + price);
    }
}