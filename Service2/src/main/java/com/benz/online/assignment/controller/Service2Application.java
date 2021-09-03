package com.benz.online.assignment.controller;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages={"com.benz.online.assignment.services","com.benz.online.assignment.controller",
		"com.benz.online.assignment.constants","com.benz.online.assignment.domain","com.benz.online.assignment.config",
		"com.benz.online.assignment.util"})
public class Service2Application {
	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);

	}
}
