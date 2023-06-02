package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ErrorUnauthorized extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErrorUnauthorized(String message) {
		super(message);
	}

}
