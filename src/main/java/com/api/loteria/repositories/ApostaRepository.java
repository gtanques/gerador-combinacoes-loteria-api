package com.api.loteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.loteria.entities.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

}
