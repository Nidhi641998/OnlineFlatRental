
package com.cg.onlineflatrental;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

import org.slf4j.*;

@SpringBootApplication
public class OnlineFlatRentalApp { 

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Logger logger=LoggerFactory.getLogger(OnlineFlatRentalApp.class);
		Integer adminId=1234;
		String password="admin";
		System.out.println("Enter the credentials:");
		System.out.println("Enter AdminId");
		Integer ipId=sc.nextInt();
		System.out.println("Enter password");
		String ipPassword=sc.next();
		if(ipId.equals(adminId) && ipPassword.equals(password)) {
		SpringApplication.run(OnlineFlatRentalApp.class, args);
		logger.info("In main method...");
		System.out.println("------------------SERVER started---------PORT=8086--------------"
				+ "");
		}else {
			System.out.println("Try again with valid credentials...");
			System.exit(0);
		}
	}  
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}

