package com.hallak.AssignmentLogicService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AssignmentLogicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentLogicServiceApplication.class, args);
	}

}
