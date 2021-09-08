package com.api.loteria.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroHandler {

	private final MessageSource messageSource;

	@Autowired
	public ErroHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<?> handler(MethodArgumentNotValidException exception){
		List<Object> listaDeErros = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeEntradaResponse response = new ErroDeEntradaResponse(e.getField(), mensagem);
			listaDeErros.add(response);
		});

		return listaDeErros;
	}

	@ExceptionHandler(ExceptionPersonalizada.class)
	public ResponseEntity<ExceptionResponse> erroResponsePersonalizado(ExceptionPersonalizada e) {
		ExceptionPersonalizada exceptionPersonalizada = new ExceptionPersonalizada(e.getMessage(), e.getStatus());
		return ResponseEntity
				.status(exceptionPersonalizada.getStatus())
				.body(new ExceptionResponse(exceptionPersonalizada.getMessage()));
	}

}
