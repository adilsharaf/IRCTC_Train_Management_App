package com.tcs.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorInfo {

	private String message;
	private HttpStatus status;
	private LocalDateTime createdAt;
	
	
	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorInfo(String message, HttpStatus status, LocalDateTime createdAt) {
		super();
		this.message = message;
		this.status = status;
		this.createdAt = createdAt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
