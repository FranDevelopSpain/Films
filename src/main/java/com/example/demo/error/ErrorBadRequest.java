package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ErrorBadRequest extends RuntimeException{

	private static final long serialVersionUID = 1L;

	//Constructor para poder poner un mensaje concreto cuando se lance el error
	public ErrorBadRequest(String message) {
		super(message);
	}

	
}
