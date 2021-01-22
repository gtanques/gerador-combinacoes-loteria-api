package com.api.loteria.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ApostaUtility {

	private String formatarCombinacao(String combinacao) {
		String combinacaoFormatada;
		combinacaoFormatada = combinacao;
		combinacaoFormatada = combinacaoFormatada.replaceAll(", ", "-");
		combinacaoFormatada = combinacaoFormatada.replaceAll("[\\[\\]]", "");

		return combinacaoFormatada;
	}

	private String AdicionarZeroEsquerda(String combinacao) {
		List<String> listaCombinacao = new ArrayList<>();
		String[] arrayCombinacao = combinacao.split("-");

		for (String a : arrayCombinacao) {
			listaCombinacao.add((a.length() == 1) ? "0" + a : a);
		}

		combinacao = listaCombinacao.toString();
		combinacao = formatarCombinacao(combinacao);
		return combinacao;
	}

	public String gerarCombinacao() {
		List<Integer> listaCombinacao = new ArrayList<>();
		Random random = new Random();
		int aleatorio;
		int contador = 0;

		String combinacao;
		while (true) {
			aleatorio = random.nextInt(59) + 1;
			if (!listaCombinacao.contains(aleatorio)) {
				listaCombinacao.add(aleatorio);
				contador += 1;
			}
			if (contador >= 6) {
				Collections.sort(listaCombinacao);
				combinacao = listaCombinacao.toString();
				combinacao = formatarCombinacao(combinacao);

				break;
			}
		}

		combinacao = AdicionarZeroEsquerda(combinacao);

		return combinacao;
	}
}
