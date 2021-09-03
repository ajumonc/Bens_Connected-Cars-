package com.benz.online.assignment.controller;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/service1/store-data"), regex("/service1/update-data"),regex("/service1/read-data"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Benz-Connected Cars Platform Developer")
				.description("Microservice Online assessment- Service 1")
				.contact("Ajumon Joy").version("1.0").build();
	}

}