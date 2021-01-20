package com.api.loteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.loteria.entities.Aposta;
import com.api.loteria.entities.Usuario;
import com.api.loteria.repositories.ApostaRepository;
import com.api.loteria.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ApostaRepository apostaRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public Usuario insert(Usuario entity) {
		Usuario usuario = new Usuario(null, entity.getEmail());
		
		for(Aposta a : entity.getApostas()) {
			Aposta aposta = apostaRepository.getOne(a.getId());
			usuario.getApostas().add(aposta);
		}
		
		usuario = usuarioRepository.save(usuario);
		return usuarioRepository.save(usuario);
	}
}
