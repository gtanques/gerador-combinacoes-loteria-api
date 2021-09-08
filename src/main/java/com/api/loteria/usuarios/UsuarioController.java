package com.api.loteria.usuarios;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "API REST usuarios")
@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	@Transactional
	@ApiOperation(value="Cria usuário")
	ResponseEntity<?> novoUsuario(@RequestBody @Valid UsuarioRequest request){
		Usuario usuario = request.paraUsuario(usuarioRepository);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().body(new UsuarioResponse(usuario));
	}

}

