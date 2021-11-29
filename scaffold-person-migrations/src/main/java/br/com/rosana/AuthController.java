package br.com.rosana;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.repository.UserRepository;
import br.com.rosana.security.AccountCredentialsVO;
import br.com.rosana.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository repository;
	
	@Tag(name= "Authenticates a user and returns a token")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		//ResponseEntity representa toda a resposta HTTP: status code, headers, and body.
		//ISSO SIGNIFICA QUE O RETORNO DO MÉTODO SERÁ UM HTTP RESPONSE
		//RESPONSE ENTITY DEVE SER USADO A PARTIR DO ENDPOINT (CONTROLLER?)
		try {
			//DADOS DE ENTRADA (REQUEST)
			var username = data.getUsername();
			var pasword = data.getPassword();
			
			//AUTENTICACAO.
			/**
			 * TENTA AUTENTICAR OS DADOS PASSADOS, E RETORNA UM OBJETO DE AUTENTICACAO 
			 * TOTALMENTE POPULADO (including granted authorities) SE BEM SUCEDIDO.
			 * SE NÃO FOR BEM SUCEDIDO, OU SEJA, SE NÃO CONSEGUIR AUTENTICAR, CAI NA EXCEÇÃO*/
		    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));
			
			//VÊ SE O USUÁRIO EXISTE NO REPOSITÓRIO (PUXA DO BANCO)
			var user = repository.findByUserName(username);
			var token = "";
			
			//SE O USUÁRIO EXISTIR, CRIA UM TOKEN COM O USERNAME E OS PAPEIS
			if (user != null) {
				token = tokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
			//vai retornar uma resposta http com o body contendo username e token criados
			// A shortcut for creating a {@code ResponseEntity} with the given body
			// * and the status set to {@linkplain HttpStatus#OK OK}.
			// * @param body the body of the response entity (possibly empty)
			
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}
}
