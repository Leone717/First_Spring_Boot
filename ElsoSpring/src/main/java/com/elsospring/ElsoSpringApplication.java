package com.elsospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class ElsoSpringApplication {
	
	@Bean(name = "gyula")
	@Profile("dev")
	public Person giveMeADevPerson() {
		return new Person("dev");
	}
	
	@Bean(name = "gyula")
	@Profile("prod")
	public Person giveMeAProdPerson() {
		return new Person("prod");
	}

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);
		System.out.println(ct.getBean("gyula"));
		
	}
	
}
