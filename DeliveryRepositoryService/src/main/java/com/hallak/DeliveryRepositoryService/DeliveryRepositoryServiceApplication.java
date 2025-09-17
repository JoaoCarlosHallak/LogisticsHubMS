package com.hallak.DeliveryRepositoryService;

import com.hallak.shared_libraries.dtos.config.Async.SharedMQCommonConfig;
import com.hallak.shared_libraries.dtos.config.Async.SharedMQConsumerConfig;
import com.hallak.shared_libraries.dtos.config.Async.SharedMQProducerConfig;
import com.hallak.shared_libraries.dtos.config.utils.ConfigUtils;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@Import({ConfigUtils.class, SharedMQConsumerConfig.class, SharedMQCommonConfig.class})
public class DeliveryRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryRepositoryServiceApplication.class, args);
	}

}
