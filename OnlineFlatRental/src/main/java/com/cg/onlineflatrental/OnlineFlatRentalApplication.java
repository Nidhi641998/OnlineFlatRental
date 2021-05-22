package com.cg.onlineflatrental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class OnlineFlatRentalApplication {
	
	 private static final Logger logger = LoggerFactory.getLogger(OnlineFlatRentalApplication.class);
		
			
			
	public static void main(String[] args) {
		
		logger.info("===Online Flat Rental project started===");
		SpringApplication.run(OnlineFlatRentalApplication.class, args);
	}

}
