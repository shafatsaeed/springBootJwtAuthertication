package com.techteam.springBootJwtAuthertication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.techteam.springBootJwtAuthertication")
public class SpringBootJwtAutherticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAutherticationApplication.class, args);
	}

}
