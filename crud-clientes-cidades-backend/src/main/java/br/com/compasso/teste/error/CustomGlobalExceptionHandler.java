package br.com.compasso.teste.error;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
		
	
	  protected ResponseEntity<Object>
	  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	  headers, HttpStatus status, WebRequest request){ Map<String, Object> body =
	  new LinkedHashMap<>(); body.put("timestamp", new Date()); body.put("status",
	  status.value());
	  
	  List<String> errors = ex.getBindingResult() .getFieldErrors() .stream()
	  .map(x -> x.getDefaultMessage()) .collect(Collectors.toList());
	  
	  body.put("errors", errors);
	  
	  return new ResponseEntity<>(body, headers, status); }
	 
	
	@ExceptionHandler(CidadeNotFoundException.class)
	public ResponseEntity<CustomErrorResponse>cidadeNotFound(CidadeNotFoundException ex, WebRequest request){
		 CustomErrorResponse errors = new CustomErrorResponse();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.BAD_REQUEST.value());

	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler (ClienteNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> clienteNotFound(ClienteNotFoundException ex, WebRequest request){
		CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler (SexoValidationException.class)
	public ResponseEntity<CustomErrorResponse> sexoValidation(SexoValidationException ex, WebRequest request){
		CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		System.out.println("teste"+ex.getCause());
		if(ex.getCause() instanceof InvalidFormatException) {
		CustomErrorResponse error = new CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setError("Formato de data invalido: dd/MM/yyyy");
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(status);
	}
	
	
}
