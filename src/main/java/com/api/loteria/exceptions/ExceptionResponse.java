package com.api.loteria.exceptions;

public class ExceptionResponse {
    String mensagem;

    public ExceptionResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
