package com.api.loteria.apostas;

import com.api.loteria.exceptions.ExceptionPersonalizada;
import com.api.loteria.usuarios.Usuario;
import com.api.loteria.usuarios.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Email;
import java.util.*;
import java.util.stream.Collectors;

public class ApostaRequest {

    @Email
    private final String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ApostaRequest(String email) {
        this.email = email;
    }

    public Aposta paraAposta(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ExceptionPersonalizada("E-mail " + email + " não encontrado", HttpStatus.NOT_FOUND));
        return new Aposta(gerarCombinacao(), usuario);
    }

    private String gerarCombinacao() {
        List<Integer> listaCombinacao = new ArrayList<>();
        Random random = new Random();
        int contador = 0;

        String combinacao;
        while (true) {
            int aleatorio = random.nextInt(59) + 1;
            if (!listaCombinacao.contains(aleatorio)) {
                listaCombinacao.add(aleatorio);
                contador += 1;
            }
            if (contador >= 6) {
                Collections.sort(listaCombinacao);
                combinacao = AdicionarZeroEsquerda(formatarCombinacao(listaCombinacao.toString()));

                break;
            }
        }

        return combinacao;
    }

    private String formatarCombinacao(String combinacao) {
        return combinacao.replaceAll(", ", "-").replaceAll("[\\[\\]]", "");
    }

    private String AdicionarZeroEsquerda(String combinacao) {
        String[] arrayCombinacao = combinacao.split("-");
        List<String> listaCombinacaoFormatada = Arrays.stream(arrayCombinacao)
                .map(dezena -> (dezena.length() == 1) ? "0" + dezena : dezena)
                .collect(Collectors.toList());

        return formatarCombinacao(listaCombinacaoFormatada.toString());
    }
}
