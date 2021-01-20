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

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApostaRepository apostaRepository;
		
	@Override
	public void run(String... args) throws Exception {
		
		// Usuarios
		Usuario u1 = new Usuario(null, "joao@gmail.com");
		Usuario u2 = new Usuario(null, "maria@gmail.com");
		Usuario u3 = new Usuario(null, "pedro@gmail.com");
		Usuario u4 = new Usuario(null, "lucas@gmail.com");
		Usuario u5 = new Usuario(null, "jose@gmail.com");
		
		// Apostas
		Aposta a1 = new Aposta(null, "01-02-03-04-05");
		a1.setUsuario(u1);
		
		Aposta a2 = new Aposta(null, "06-07-08-09-10");
		a2.setUsuario(u2);
		
		Aposta a3 = new Aposta(null, "11-12-13-14-15");
		a3.setUsuario(u3);
		
		Aposta a4 = new Aposta(null, "16-17-18-19-20");
		a4.setUsuario(u4);

		Aposta a5 = new Aposta(null, "01-02-03-04-05");
		a5.setUsuario(u5);
									
		// Inserir dados 
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		apostaRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));			
		
	}	
	
}
