package com.api.loteria.apostas;

import com.api.loteria.usuarios.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apostas")
public class ApostaController {

    private final UsuarioRepository usuarioRepository;
    private final ApostaRepository apostaRepository;

    @Autowired
    public ApostaController(UsuarioRepository usuarioRepository, ApostaRepository apostaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.apostaRepository = apostaRepository;
    }

    @PostMapping
    @Transactional
    @ApiOperation(value="Cria combinação")
    public ResponseEntity<ApostaResponse> gerarAposta(@Valid @RequestBody ApostaRequest request) {
        Aposta aposta = request.paraAposta(usuarioRepository);
        apostaRepository.save(aposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{emailUsuario}").buildAndExpand(aposta.getUsuario().getEmail())
                .toUri();

        return ResponseEntity.created(uri).body(new ApostaResponse(aposta));
    }

    @GetMapping("/{email}")
    @ApiOperation(value="Busca combinações por e-mail")
    public ResponseEntity<?> buscarApostasPorEmail(@PathVariable String email){
        List<Aposta> listaDeApostas = apostaRepository.findAllByEmail(email);
        return ResponseEntity.ok().body(listaDeApostas.stream().map(ApostaResponse::new).collect(Collectors.toList()));
    }

}
