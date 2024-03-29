package com.api.loteria.apostas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApostaRequestTest {

    private ApostaRequest apostaRequest;

    @BeforeEach
    void setUp() {
        apostaRequest = new ApostaRequest("teste@teste.com");
    }

    @Test
    public void deveConsiderarCombinacaoVerdadeira() {
        var combinacao = apostaRequest.gerarCombinacao();
        System.out.println(combinacao.length());
        assertEquals(17, combinacao.length());
        String regexCombinacao = "^[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-([0-5]?[0-9]|60)$";
        assertTrue(combinacao.matches(regexCombinacao));
    }

}