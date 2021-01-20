package com.api.loteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.loteria.entities.Aposta;
import com.api.loteria.repositories.ApostaRepository;

@Service
public class ApostaService {

	@Autowired
	private ApostaRepository repository;

	public List<Aposta> findAll() {
		return repository.findAll();
	}

	public Aposta insert(Aposta entity) {
		return repository.save(entity);
	}
}
