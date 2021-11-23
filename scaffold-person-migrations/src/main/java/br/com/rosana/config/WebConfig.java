package br.com.rosana.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.rosana.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	//podemos adicionar o cors por aqui ou direto na classe controller ou em seus métodos que a gente quiser
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**")
		// vai disponibilizar todos os recursos a todo mundo. 
		//por padrão, Funciona muito bem com os métodos HTTP principais.não funciona para os métodos OPTIONS E PATCH
		//mas posso adicionar os métodos que eu quiser:
		.allowedMethods("GET","POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		//PARA USAR VIA QUERY PARAMETER:
		//exemplo para consulta: 
		//localhost:8080/api/person/v1/6?mediaType=xml
		//localhost:8080/api/person/v1/6?mediaType=json
		//localhost:8080/api/person/v1?mediaType=json
		//localhost:8080/api/person/v1?mediaType=xml
		//localhost:8080/api/person/v1/6?mediaType=x-yaml  //(se o yaml estiver configurado)
		
		//COM QUERY PARAM:
		/*
		 configurer.favorParameter(true) 
		 .parameterName("mediaType")
		 .ignoreAcceptHeader(true) 
		 .useRegisteredExtensionsOnly(false)
		 .defaultContentType(MediaType.APPLICATION_JSON) 
		 .mediaType("json", MediaType.APPLICATION_JSON) 
		 .mediaType("xml", MediaType.APPLICATION_XML)
		 .mediaType("x-yaml", MEDIA_TYPE_YML);
		*/
		
		//PARA USAR VIA HEADER PARAMETER:
		//simplesmente coloque no header, no postman:
		//na hora de enviar a requisição GET, a key Accept com o value application/json ou application/xml
		//na hora de enviar a requisição POST ou PUT, a key Content-Type com o value application/x-yaml ou application/json, etc
		
		//COM HEADER PARAM:
		
		 configurer.favorParameter(false) 
		 .ignoreAcceptHeader(false)
		 .useRegisteredExtensionsOnly(false)
		 .defaultContentType(MediaType.APPLICATION_JSON)
		 .mediaType("json",MediaType.APPLICATION_JSON) 
		 .mediaType("xml",MediaType.APPLICATION_XML)
		 .mediaType("x-yaml", MEDIA_TYPE_YML);
		 
		
		
	}
}
