package com.benz.online.assignment.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.benz.online.assignment.services","com.benz.online.assignment.controller","com.benz.online.assignment.constants",
		"com.benz.online.assignment.model","com.benz.online.assignment.utils","com.benz.online.assignment.rabbitMQConfig"})
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);

	}

}
