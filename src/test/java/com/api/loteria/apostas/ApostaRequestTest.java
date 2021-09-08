package com.api.loteria.apostas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApostaRequestTest {

    private ApostaRequest apostaRequest;
    private final String regexCombinacao = "^[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-[0-5][0-9]-([0-5]?[0-9]|60)$";

    @BeforeEach
    void setUp() {
        apostaRequest = new ApostaRequest("teste@teste.com");
    }

    @Test
    public void deveConsiderarCombinacaoVerdadeira() {
        var combinacao = apostaRequest.gerarCombinacao();
        System.out.println(combinacao.length());
        assertEquals(17, combinacao.length());
        assertTrue(combinacao.matches(regexCombinacao));
    }

    @Test
    public void deveConsiderarCombinacoesFalsas() {
        var combinacao1 = "01-02-03-05-51-61";
        var combinacao2 = "1-60-03-05-51-71";
        var combinacao3 = "02-03-05-51-55";
        var combinacao4 = "0-1-2-3-25";

        assertFalse(combinacao1.matches(regexCombinacao));
        assertFalse(combinacao2.matches(regexCombinacao));
        assertFalse(combinacao3.matches(regexCombinacao));
        assertFalse(combinacao4.matches(regexCombinacao));
    }

}