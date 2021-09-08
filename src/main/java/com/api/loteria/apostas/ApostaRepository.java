package com.api.loteria.apostas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ApostaRepository extends JpaRepository<Aposta, String> {
    @Query("SELECT p FROM Aposta p WHERE p.usuario.email = :email")
    List<Aposta> findAllByEmail(@RequestParam String email);
}