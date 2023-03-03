package com.cognizant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler class
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNameNumericException.class)
	public ResponseEntity<CustomErrorResponse> handlesUserNameNumericException(
			UserNameNumericException userNameNumericException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userNameNumericException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlesUserNotFoundException(
			UserNotFoundException userNotFoundException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorResponse> methodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setMessage(ex.getLocalizedMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
}
