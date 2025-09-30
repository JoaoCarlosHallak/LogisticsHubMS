package com.hallak.DeliveryRepositoryService;

import com.hallak.shared_libraries.config.Async.SharedMQCommonConfig;
import com.hallak.shared_libraries.config.Async.SharedMQConsumerConfig;
import com.hallak.shared_libraries.config.utils.ConfigUtils;
import com.hallak.shared_libraries.handlers.GlobalRestControllerAdvice;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@EnableFeignClients
@Import({ConfigUtils.class, SharedMQConsumerConfig.class, SharedMQCommonConfig.class, GlobalRestControllerAdvice.class})
public class DeliveryRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryRepositoryServiceApplication.class, args);
	}

}
