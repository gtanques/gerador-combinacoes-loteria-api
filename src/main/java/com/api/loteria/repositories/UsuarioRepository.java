package com.api.loteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.loteria.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
