package com.hallak.AssignmentLogicService.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceConsumerMQConfig {
    @Value("${rabbitmq.queue.order}")
    private String orderQueueName;




    @Bean
    public Declarables declarables() {
        Queue order = new Queue(orderQueueName, true, false, false);
        DirectExchange exchange = ExchangeBuilder.directExchange("app.exchange").durable(true).build();
        Binding bOrder = BindingBuilder.bind(order).to(exchange).with("order.routing");

        return new Declarables(order, exchange, bOrder);
    }


}





