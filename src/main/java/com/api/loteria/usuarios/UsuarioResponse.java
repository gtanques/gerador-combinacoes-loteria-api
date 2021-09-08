package com.api.loteria.usuarios;

public class UsuarioResponse {
    String nome, email;

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
