package com.api.loteria.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.loteria.services.exceptions.ExceptionPersonalizada;
import com.api.loteria.services.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class ExcecaoController {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(RecursoNaoEncontradoException e,
			HttpServletRequest requisicao) {
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;

		ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), e.getMessage(), error,
				requisicao.getRequestURI());

		return ResponseEntity.status(status).body(erroPadrao);

	}

	@ExceptionHandler(ExceptionPersonalizada.class)
	public ResponseEntity<ErroPadrao> recursoInvalido(ExceptionPersonalizada e, HttpServletRequest requisicao) {
		String error = "Recurso inválido";
		HttpStatus status = HttpStatus.FORBIDDEN;

		ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), e.getMessage(), error,
				requisicao.getRequestURI());

		return ResponseEntity.status(status).body(erroPadrao);

	}

}
