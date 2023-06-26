package com.edu.certus.msprofesor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients( "com.edu.certus.msprofesor.client" )
public class MsProfesorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProfesorApplication.class, args);
	}

}
