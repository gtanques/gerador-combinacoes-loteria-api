package com.api.loteria.exceptions;


import org.springframework.http.HttpStatus;

public class ExceptionPersonalizada extends RuntimeException{

	private final HttpStatus status;

	public ExceptionPersonalizada(String mensagem, HttpStatus status) {
		super(mensagem);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
