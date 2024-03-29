package com.agenda.contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
// @EnableWebMvc
// @Configuration
// @ComponentScan(basePackages = { "com.agenda.contactos.controller" }) 
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
