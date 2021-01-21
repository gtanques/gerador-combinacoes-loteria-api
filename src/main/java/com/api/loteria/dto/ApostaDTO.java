package com.api.loteria.dto;

import java.io.Serializable;

import com.api.loteria.entities.Aposta;

public class ApostaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String combinacao;

	public ApostaDTO() {

	}

	public ApostaDTO(Aposta entity) {
		id = entity.getId();
		combinacao = entity.getCombinacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCombinacao() {
		return combinacao;
	}

	public void setCombinacao(String combinacao) {
		this.combinacao = combinacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApostaDTO other = (ApostaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
