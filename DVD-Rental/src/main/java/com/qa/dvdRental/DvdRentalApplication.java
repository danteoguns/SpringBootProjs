package com.qa.dvdRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DvdRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DvdRentalApplication.class, args);
	}
}
