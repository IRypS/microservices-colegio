package com.edu.certus.msalumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.edu.certus.msalumno.client")
public class MsAlumnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAlumnoApplication.class, args);
	}

}
