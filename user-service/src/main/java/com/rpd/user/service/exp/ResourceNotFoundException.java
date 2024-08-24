package com.rpd.user.service.exp;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found on the server!!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
