package com.hallak.DeliveryRepositoryService.Config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceConsumerMQConfig {

    @Value("${rabbitmq.queue.delivery}")
    private String deliveryQueueName;




    @Bean
    public Declarables declarables() {
        Queue delivery = new Queue(deliveryQueueName, true, false, false);
        DirectExchange exchange = ExchangeBuilder.directExchange("app.exchange").durable(true).build();
        Binding bDelivery = BindingBuilder.bind(delivery).to(exchange).with("delivery.routing");

        return new Declarables(delivery, exchange, bDelivery);
    }


}





