package com.api.loteria.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.loteria.dto.UsuarioDTO;
import com.api.loteria.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuarios")
@Api(value = "API REST usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	@ApiOperation(value="Retorna uma lista com todos os usuários e suas combinações")
	public ResponseEntity<List<UsuarioDTO>> buscarTudo() {
		List<UsuarioDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{email}")
	@ApiOperation(value="Retorna o usuário e suas combinações")
	public ResponseEntity<UsuarioDTO> buscarViaEmail(@PathVariable String email) {
		UsuarioDTO usuario = service.buscarPorEmail(email);
		
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping(value = "/{email}")
	@ApiOperation(value="Salva um usuário e uma combinação")
	public ResponseEntity<UsuarioDTO> inserirUsuarioEmailUmaAposta(@PathVariable String email) {
		UsuarioDTO dto = service.insertViaEmail(email);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}

	@PostMapping(value = "/{emailUsuario}/{quantidadeApostas}")
	@ApiOperation(value="Salva um usuário com combinações")
	public ResponseEntity<UsuarioDTO> inserirUsuarioEmailComApostas(@PathVariable String emailUsuario,
			@PathVariable Integer quantidadeApostas) {
		UsuarioDTO dto = service.insertViaEmailMuitasApostas(emailUsuario, quantidadeApostas);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{emailUsuario}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}

}
