package com.rpd.rating.exp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rpd.rating.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponse response = ApiResponse.builder()
				.message(message)
				.success(false)
				.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
