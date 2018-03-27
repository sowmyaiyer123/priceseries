package com.calculation.engine.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceProducer {
    private static final Logger logger = LoggerFactory.getLogger(PriceProducer.class);
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PriceProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(double price) {
        logger.info("Published price: " + price);
        rabbitTemplate.convertAndSend(SpringAmqpConfig.queueName, price);
    }
}