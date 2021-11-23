package br.com.rosana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

//essa é uma exceção customizada! 

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;
	
	public InvalidJwtAuthenticationException(String exception) {
		super(exception);
	}
	
}