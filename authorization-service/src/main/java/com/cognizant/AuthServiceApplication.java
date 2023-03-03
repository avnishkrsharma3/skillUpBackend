package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is the main class for Authorisation class
 * 
 * @author Avnish
 *
 */
@EnableSwagger2
@SpringBootApplication
public class AuthServiceApplication {

	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
