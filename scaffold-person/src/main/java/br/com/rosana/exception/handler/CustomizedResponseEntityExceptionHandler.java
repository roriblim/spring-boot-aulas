package br.com.rosana.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rosana.exception.ExceptionResponse;
import br.com.rosana.exception.UnsuportedMathOperationException;

//ver https://mkyong.com/spring-boot/spring-rest-error-handling-example/

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	//existe suporte para um @ExceptionHandler global ou de uma classe customizada com a anotação @ControllerAdvice 
	//com o @ExceptionHandler. O @ExceptionHandler permite que, ao dar a exceção, ele execute esse método.
	
	//essa vai ser a resposta genérica para os erros:
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		//protected ResponseEntity<Object> handleConflict ( RuntimeException ex, WebRequest request) {...
		//ExceptionResponse foi a classe criada por nós, que conterá data, mensagem e detalhes
		
		ExceptionResponse resposta = 
				new ExceptionResponse(
						new Date(),
						ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UnsuportedMathOperationException.class)
	
	public final ResponseEntity<ExceptionResponse> handleBaaadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse resposta = 
				new ExceptionResponse(
						new Date(),
						ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
	}

}