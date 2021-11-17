package br.com.rosana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//essa é uma exceção customizada! Podemos fazer ela com Spring Boot.

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnsuportedMathOperationException(String exception) {
		super(exception);
	}
	
}