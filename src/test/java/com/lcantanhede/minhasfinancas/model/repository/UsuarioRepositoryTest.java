package com.lcantanhede.minhasfinancas.model.repository;

import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve verificar a existencia de um email")
    void verificarEmail() {

        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
        usuarioRepository.save(usuario);

        boolean result = usuarioRepository.existsByEmail("usuario@email.com");

        Assertions.assertThat(result).isTrue();
    }
}