package com.cts.cohort.exception;

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
	
	@ExceptionHandler(CohortNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(CohortNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(CourseNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * This handles all the response for CollateralAlreadyExistsException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(CohortAlreadyExistException.class)
	public ResponseEntity<ApiErrorResponse> handleCohortAlreadyExistException(
			CohortAlreadyExistException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
//	@ExceptionHandler(TokenNotFoundException.class)
//	public ResponseEntity<ExceptionDetails> handleAuthorizationException(TokenNotFoundException exception) {
//		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
//		log.error(exception.getMessage());
//		return new ResponseEntity<>(exceptionDetail, HttpStatus.FORBIDDEN);
//		}
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
	
}