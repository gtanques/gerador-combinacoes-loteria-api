package com.api.loteria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.api.loteria.entities.Aposta;
import com.api.loteria.entities.Usuario;

public class ApostadorDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private List<Aposta> apostas = new ArrayList<>();

	ApostadorDto() {

	}

	public ApostadorDto(Usuario entity) {		
		id = entity.getId();
		email = entity.getEmail();
		apostas = entity.getApostas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

}
