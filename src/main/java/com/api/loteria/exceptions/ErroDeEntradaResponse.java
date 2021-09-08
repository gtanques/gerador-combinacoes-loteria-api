package com.api.loteria.exceptions;

public class ErroDeEntradaResponse {
    private final String campo;
    private final String mensagem;

    public ErroDeEntradaResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
