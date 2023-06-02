package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ErrorNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorNotFound(String message) {
		super(message);
	}
	
}
