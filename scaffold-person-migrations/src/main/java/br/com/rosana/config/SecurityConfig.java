package br.com.rosana.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.rosana.security.JwtConfigurer;
import br.com.rosana.security.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
		//CRIA O CODIFICADOR DE SENHA (passwordEncoder)
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
		//QUEM VAI GERENCIAR AS AUTENTICAÇÕES
	}
	
	//configura a aplicação:
	protected void configure(HttpSecurity http) throws Exception {
		//QUEM VAI GARANTIR QUE SÓ VÃO ACESSAR AS URLS QUEM TIVER PERMISSÃO
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/auth/signin", "/api-docs/**", "/swagger-ui.html**").permitAll()//nesses casos, nao precisa autenticar
				.antMatchers("/api/**").authenticated() //todos que chegarem por aqui precisam estar autenticados
				.antMatchers("/users").denyAll() //aqui, ninguém pode acessar
			.and()
			.apply(new JwtConfigurer(tokenProvider)); //aqui, vai chamar instanciar o JwtConfigurer com o tokenProvider
	}
}
