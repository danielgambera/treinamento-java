package br.com.ehmf.AppContatos.AppContatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e)
	{
		Erro erro = new Erro(e.getMessage(), "APPCONTATOS");
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(BadRequestException e)
	{
		Erro erro = new Erro(e.getMessage(), "APPCONTATOS");
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
}