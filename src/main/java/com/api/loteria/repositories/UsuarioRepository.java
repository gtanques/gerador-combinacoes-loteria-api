package com.api.loteria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.loteria.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT DISTINCT obj FROM Usuario obj JOIN FETCH obj.apostas")
	List<Usuario> buscarUsuariosComApostas();
}
