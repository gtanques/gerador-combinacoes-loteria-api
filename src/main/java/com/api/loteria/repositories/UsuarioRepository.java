package com.api.loteria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.loteria.dto.UsuarioDTO;
import com.api.loteria.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT DISTINCT obj FROM Usuario obj JOIN FETCH obj.apostas")
	List<UsuarioDTO> buscarUsuariosComApostas();

	@Query("SELECT DISTINCT obj FROM Usuario obj JOIN FETCH obj.apostas WHERE obj.email = :email")
	Optional<UsuarioDTO> buscarApostasPorEmail(@Param("email") String email);
}