package com.hallak.AssignmentLogicService;

import com.hallak.shared_libraries.config.Async.SharedMQConsumerConfig;
import com.hallak.shared_libraries.config.Async.SharedMQCommonConfig;
import com.hallak.shared_libraries.config.Async.SharedMQProducerConfig;
import com.hallak.shared_libraries.config.utils.ConfigUtils;
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
@Import({ConfigUtils.class, SharedMQProducerConfig.class, SharedMQConsumerConfig.class, SharedMQCommonConfig.class})
public class AssignmentLogicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentLogicServiceApplication.class, args);
	}

}
