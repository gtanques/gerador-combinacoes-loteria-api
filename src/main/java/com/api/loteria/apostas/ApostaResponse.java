package com.api.loteria.apostas;

public class ApostaResponse {
    private final String combinacao;

    public ApostaResponse(Aposta aposta) {
        this.combinacao = aposta.getCombinacao();
    }

    public String getCombinacao() {
        return combinacao;
    }
}
