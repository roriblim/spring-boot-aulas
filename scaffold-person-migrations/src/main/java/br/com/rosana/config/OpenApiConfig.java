package br.com.rosana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
				.title("Aulas de RESTful API com Spring Boot 2.5.6")
				.version("v1")
				.description("Essa é minha API das aulas da Udemy sobre REST API.")
				.termsOfService("Terms Of Service Url")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}


	
}