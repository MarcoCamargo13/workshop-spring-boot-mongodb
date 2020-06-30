package com.educandoweb.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice //esta classe possiveis erros das requisões
public class ResourdeExceptionHandler {

	//ResponseEntity<StandardError> tipo de retorno
	//tipo dda excessão que será tratada
	// HttpServletRequest request exigencia do Spring
	//System.currentTimeMillis() tempo autal do sistema //depois converte para numero inteiro status.value()
	
	HttpStatus status = HttpStatus.NOT_FOUND; //httpStatus passa o erro que sera atribuido a variave.
	@ExceptionHandler(ObjectNotFoundException.class)//este é um padrão que é necessario
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){//HttpServletRequest requestcaminhho do erro
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
