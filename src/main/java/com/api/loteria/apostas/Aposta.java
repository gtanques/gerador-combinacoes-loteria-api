package com.api.loteria.apostas;

import com.api.loteria.usuarios.Usuario;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_aposta")
public class Aposta{

	@Id
	private final String id = UUID.randomUUID().toString();

	@Column(nullable = false)
	private String combinacao;

	@Column(nullable = false, updatable = false)
	private final Instant criadoEm = Instant.now();

	@ManyToOne
	private Usuario usuario;

	@Deprecated
	private Aposta() {
	}

	public Aposta(String combinacao, Usuario usuario) {
		this.combinacao = combinacao;
		this.usuario = usuario;
	}

	public String getCombinacao() {
		return combinacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
