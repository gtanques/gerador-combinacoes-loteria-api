package com.api.loteria.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(Object obj) {
		super("Recurso não encontrado" + obj);
	}

}
