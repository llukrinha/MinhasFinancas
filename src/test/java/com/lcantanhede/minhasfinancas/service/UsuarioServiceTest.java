package com.lcantanhede.minhasfinancas.service;

import com.lcantanhede.minhasfinancas.exception.RegraNegocioException;
import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import com.lcantanhede.minhasfinancas.model.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve validar um email")
    void validarEmail() {

        usuarioRepository.deleteAll();

        Assertions.assertDoesNotThrow(() -> usuarioService.validarEmail("email@email.com"));
    }

    @Test
    @DisplayName("Deve lançar error ao validar email quando existir email cadastrado")
    void validarEmailError() {

        Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
        usuarioRepository.save(usuario);

        Assertions.assertThrows(RegraNegocioException.class, () -> usuarioService.validarEmail("email@email.com"));

    }
}

