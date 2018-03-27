package com.calculation.engine.rabbitmq;

import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@PropertySource("classpath:/application.properties")
public class PriceGeneratorCron {

    private final PriceProducer producer;

    public PriceGeneratorCron(PriceProducer producer) {
        this.producer = producer;
    }

    public static double generateNumber() {
        Random r = new Random();
        int multiplier = 10000;
        if (r.nextInt(100000) % 2 == 0) {
            multiplier = 1000;
        }
        return new Double((Math.random() * multiplier) + Math.random());
    }

    @Scheduled(cron = ("${cron.frequency}"))
    public void run() {
        producer.sendMessage(generateNumber());
    }

}