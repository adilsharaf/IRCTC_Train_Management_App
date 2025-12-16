package com.tcs.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value= {InvalidOperationException.class,ResourceNotFoundException.class})
	public ErrorInfo handleException(Exception exception) {
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setStatus(HttpStatus.NOT_FOUND);
		errorInfo.setCreatedAt(LocalDateTime.now());
		return errorInfo;
	}
}
