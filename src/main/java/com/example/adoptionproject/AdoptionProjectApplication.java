package com.example.adoptionproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdoptionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionProjectApplication.class, args);
	}

}
