package com.cts.user.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the class for Global Exception Handling
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * This handles all the response for CollateralNotFoundException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		
		Map<String,String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error-> errorMap.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errorMap);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(UserNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(TokenNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleAuthorizationException(TokenNotFoundException exception) {
		ApiErrorResponse exceptionDetail = new ApiErrorResponse(HttpStatus.FORBIDDEN, exception.getLocalizedMessage(), exception.getMessage(), LocalDateTime.now());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.FORBIDDEN);
		}
	/**
	 * This handles all the response for FeignStatusException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ApiErrorResponse> handleFeignStatusException(FeignException ex,
			HttpServletResponse response) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
		errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
		errorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserAllReadyExistException.class)
	public ResponseEntity<ApiErrorResponse> userAllReadyExist(UserAllReadyExistException ex,
			HttpServletResponse response) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
		errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> userAllReadyExist(Exception ex,
			HttpServletResponse response) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
		errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
		errorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}