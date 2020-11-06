package com.lcantanhede.minhasfinancas.model.repository;

import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
