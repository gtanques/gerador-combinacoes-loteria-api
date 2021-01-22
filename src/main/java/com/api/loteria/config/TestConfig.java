package com.api.loteria.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.loteria.entities.Aposta;
import com.api.loteria.entities.Usuario;
import com.api.loteria.repositories.ApostaRepository;
import com.api.loteria.repositories.UsuarioRepository;
import com.api.loteria.utilities.ApostaUtility;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApostaRepository apostaRepository;

	@Autowired
	private ApostaUtility apostaUtility;

	@Override
	public void run(String... args) throws Exception {

		// Users
		Usuario u1 = new Usuario(null, "joao@gmail.com");
		Usuario u2 = new Usuario(null, "maria@gmail.com");
		Usuario u3 = new Usuario(null, "pedro@gmail.com");
		Usuario u4 = new Usuario(null, "lucas@gmail.com");
		Usuario u5 = new Usuario(null, "jose@gmail.com");

		// Bet
		Aposta a1 = new Aposta(null, apostaUtility.gerarCombinacao());
		u1.getApostas().add(a1);
		Aposta a2 = new Aposta(null, apostaUtility.gerarCombinacao());
		u2.getApostas().add(a2);
		Aposta a3 = new Aposta(null, apostaUtility.gerarCombinacao());
		u3.getApostas().add(a3);
		Aposta a4 = new Aposta(null, apostaUtility.gerarCombinacao());
		u4.getApostas().add(a4);
		Aposta a5 = new Aposta(null, apostaUtility.gerarCombinacao());
		u5.getApostas().add(a5);

		// Insert
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		apostaRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

	}

}
