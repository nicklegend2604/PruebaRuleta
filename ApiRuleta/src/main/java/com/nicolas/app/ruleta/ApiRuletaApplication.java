package com.nicolas.app.ruleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ApiRuletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRuletaApplication.class, args);
	}

}
