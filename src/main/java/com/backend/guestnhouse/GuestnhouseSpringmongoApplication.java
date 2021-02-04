package com.backend.guestnhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GuestnhouseSpringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestnhouseSpringmongoApplication.class, args);
	}

}
