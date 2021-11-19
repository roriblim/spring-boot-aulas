package br.com.rosana.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{


	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		//PARA USAR VIA QUERY PARAMETER:
		//exemplo para consulta: 
		//localhost:8080/api/person/v1/6?mediaType=xml
		//localhost:8080/api/person/v1/6?mediaType=json
		//localhost:8080/api/person/v1?mediaType=json
		//localhost:8080/api/person/v1?mediaType=xml
		
		//COM QUERY PARAM:
		 configurer.favorParameter(true) 
		 .parameterName("mediaType")
		 .ignoreAcceptHeader(true) 
		 .useRegisteredExtensionsOnly(false)
		 .defaultContentType(MediaType.APPLICATION_JSON) 
		 .mediaType("json", MediaType.APPLICATION_JSON) 
		 .mediaType("xml", MediaType.APPLICATION_XML);
		  
		
		
		//PARA USAR VIA HEADER PARAMETER:
		//simplesmente coloque no header, no postman, 
		//na hora de enviar a requisição, a key Accept com o value application/json ou application/xml
		 
		/*//COM HEADER PARAM:
		 * configurer.favorParameter(false) .ignoreAcceptHeader(false)
		 * .useRegisteredExtensionsOnly(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON)
		 * .mediaType("json",MediaType.APPLICATION_JSON) .mediaType("xml",
		 * MediaType.APPLICATION_XML);
		 */
		
		
	}
}
