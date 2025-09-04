package com.hallak.FleetManagementService;

import com.hallak.shared_libraries.dtos.config.utils.ConfigUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ConfigUtils.class)
@EnableDiscoveryClient
public class FleetManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetManagementServiceApplication.class, args);
	}

}
