package com.Lcantanhede.minhasfinancas.model.repository;

import com.Lcantanhede.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
