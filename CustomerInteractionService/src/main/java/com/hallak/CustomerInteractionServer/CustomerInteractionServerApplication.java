package com.hallak.CustomerInteractionServer;

import com.hallak.shared_libraries.config.Async.SharedMQCommonConfig;
import com.hallak.shared_libraries.config.Async.SharedMQProducerConfig;
import com.hallak.shared_libraries.config.utils.ConfigUtils;
import com.hallak.shared_libraries.handlers.GlobalRestControllerAdvice;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@Import({ConfigUtils.class, SharedMQProducerConfig.class, SharedMQCommonConfig.class, GlobalRestControllerAdvice.class})
public class CustomerInteractionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerInteractionServerApplication.class, args);
	}

}
