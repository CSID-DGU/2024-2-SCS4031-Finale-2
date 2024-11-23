package com.finale.global.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.finale.global.exception.user.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "Resource not found";
	}
}