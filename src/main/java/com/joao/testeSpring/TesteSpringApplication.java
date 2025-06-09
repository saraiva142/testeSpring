package com.joao.testeSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan

@Profile("dev")
public class TesteSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteSpringApplication.class, args);
	}

}
