package com.calculation.engine;

import com.calculation.engine.rabbitmq.PriceProducer;
import com.calculation.engine.service.DataStoreService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(RabbitTestConfiguration.class)
@ContextConfiguration
@SpringBootTest(properties = {"cron.frequency=*/500000 * * * * *"})
public class SendReceiveMqTest implements ApplicationContextAware {

    @Autowired
    private RabbitTemplate template;
    private PriceProducer priceProducer;
    private DataStoreService dataStoreService;

    @Test
    public void messageCanBeSentAndConsumedAsExpected() {
        int testData = 1234;
        template.convertAndSend(RabbitTestConfiguration.queueName, testData);
        assertEquals(template.receiveAndConvert(RabbitTestConfiguration.queueName), testData);
        testData++;
        template.convertAndSend(RabbitTestConfiguration.queueName, testData);
        assertEquals(template.receiveAndConvert(RabbitTestConfiguration.queueName), testData);
    }

    @Test
    @Ignore
    public void testProducerConsumer() throws InterruptedException {
        double data[] = {10, 20, 30, 40, 50};
        Arrays.stream(data).forEach(d -> priceProducer.sendMessage(d));
        Thread.sleep(1000);
        assertEquals(dataStoreService.computeAverageOfLastN(3), 40, 0.2);
        assertEquals(dataStoreService.computeAverageOfLastN(5), 30, 0.2);
        assertEquals(dataStoreService.computeAverageOfLastN(1), 0, 0.2);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.priceProducer = applicationContext.getBean(PriceProducer.class);
        this.dataStoreService = applicationContext.getBean(DataStoreService.class);
    }
}