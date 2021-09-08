package com.api.loteria.usuarios;

import com.api.loteria.apostas.Aposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String documento;

	@Column(nullable = false, updatable = false)
	private final Instant criadoEm = Instant.now();

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Aposta> apostas = new ArrayList<>();

	@Deprecated
	private Usuario(){}

	public Usuario(String email, String nome, String documento) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
}
