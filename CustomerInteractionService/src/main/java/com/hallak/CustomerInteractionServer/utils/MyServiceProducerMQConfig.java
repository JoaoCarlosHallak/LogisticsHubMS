package com.hallak.CustomerInteractionServer.utils;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyServiceProducerMQConfig {

    @Bean
    public Queue queueToSaveDispatchOrder(@Value("${rabbitmq.queue.order}") String queueName) {
        return QueueBuilder.durable(queueName).build();
    }






}