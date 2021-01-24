package com.api.loteria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.loteria.dto.UsuarioDTO;
import com.api.loteria.entities.Aposta;
import com.api.loteria.entities.Usuario;
import com.api.loteria.repositories.ApostaRepository;
import com.api.loteria.repositories.UsuarioRepository;
import com.api.loteria.services.exceptions.ExceptionPersonalizada;
import com.api.loteria.services.exceptions.RecursoNaoEncontradoException;
import com.api.loteria.services.utilities.ApostaUtility;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ApostaRepository apostaRepository;

	@Autowired
	private ApostaUtility apostaUtility;

	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		return usuarioRepository.buscarUsuariosComApostas();
	}

	@Transactional(readOnly = true)
	public UsuarioDTO buscarPorEmail(String emailUsuario) {
		Optional<UsuarioDTO> usuario = usuarioRepository.buscarApostasPorEmail(emailUsuario);
		return usuario.orElseThrow(() -> new RecursoNaoEncontradoException(emailUsuario));
	}
	
	private Boolean emailCadastrado(String emailUsuario) {		 
		Optional<UsuarioDTO> emailExistente = usuarioRepository.buscarApostasPorEmail(emailUsuario);
		
		if(emailExistente.isEmpty()) {
			return false;
		}
		
		return true;
	} 
	
	@Transactional
	public UsuarioDTO insertViaEmail(String emailUsuario) {		
		if(emailCadastrado(emailUsuario)) {
			throw new ExceptionPersonalizada("E-mail já cadastrado.");
		}
						
		Aposta aposta = new Aposta(null, apostaUtility.gerarCombinacao());
		aposta = apostaRepository.save(aposta);
		
		Usuario usuario = new Usuario(null, emailUsuario);
		usuario.getApostas().add(aposta);
		usuario = usuarioRepository.save(usuario);

		return new UsuarioDTO(usuario);
	}

	@Transactional
	public UsuarioDTO insertViaEmailMuitasApostas(String emailUsuario, Integer quantidadeApostas) {
		if(emailCadastrado(emailUsuario)) {
			throw new ExceptionPersonalizada("E-mail já cadastrado");
		}
		
		if ((quantidadeApostas > 10) || quantidadeApostas < 1) {
			throw new ExceptionPersonalizada("Quantidade de apostas inválida. o número de apostas deve ser de 1 a 10 por usuario");
		}
		
		int contador = 0;			
		List<Aposta> listaApostas = new ArrayList<Aposta>();
		Usuario usuario = new Usuario(null, emailUsuario);
				
		while ((contador != quantidadeApostas) && contador < 10) { 
			Aposta aposta = new Aposta(null, apostaUtility.gerarCombinacao());
			listaApostas.add(aposta);
			contador++;
		}

		for (Aposta a : listaApostas) {
			Aposta aposta = apostaRepository.save(a);
			usuario.getApostas().add(aposta);
		}

		usuario = usuarioRepository.save(usuario);

		return new UsuarioDTO(usuario);
	}

}
