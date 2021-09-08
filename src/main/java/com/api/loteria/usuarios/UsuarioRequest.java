package com.api.loteria.usuarios;

import com.api.loteria.exceptions.ExceptionPersonalizada;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @Email
    private final String email;

    @CPF
    private final String documento;

    @Size(max = 100)
    private final String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioRequest(String email, String documento, String nome) {
        this.email = email;
        this.documento = documento;
        this.nome = nome;
    }

    public Usuario paraUsuario(UsuarioRepository usuarioRepository) {
        boolean emailJaCadastrado = usuarioRepository.findByEmail(this.email).isPresent();

        if (emailJaCadastrado){
            throw new ExceptionPersonalizada("Email já cadastrado", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new Usuario(this.email, this.nome, this.documento);
    }
}
