package com.api.loteria.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.loteria.dto.UsuarioDTO;
import com.api.loteria.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{email}")
	public ResponseEntity<UsuarioDTO> findEmail(@PathVariable String email) {
		UsuarioDTO usuario = service.findEmail(email);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO dto) {
		dto = service.insertUsuarioAposta(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PostMapping(value = "/{email}")
	public ResponseEntity<UsuarioDTO> inserirUsuarioEmailUmaAposta(@PathVariable String email) {
		UsuarioDTO dto = service.insertViaEmail(email);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PostMapping(value = "/{emailUsuario}/{quantidadeApostas}")
	public ResponseEntity<UsuarioDTO> inserirUsuarioEmailComApostas(@PathVariable String emailUsuario,
			@PathVariable Integer quantidadeApostas) {
		UsuarioDTO dto = service.insertViaEmailMuitasApostas(emailUsuario, quantidadeApostas);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
