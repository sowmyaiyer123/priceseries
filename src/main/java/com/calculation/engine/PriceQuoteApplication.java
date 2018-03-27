package com.calculation.engine;

import com.calculation.engine.rabbitmq.SpringAmqpConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
@Import(SpringAmqpConfig.class)
public class PriceQuoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceQuoteApplication.class, args);
    }
}
