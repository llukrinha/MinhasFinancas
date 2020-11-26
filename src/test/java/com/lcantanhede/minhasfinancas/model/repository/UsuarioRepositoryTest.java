package com.lcantanhede.minhasfinancas.model.repository;

import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager entityManager;

    static Usuario criarUsuario() {
        return Usuario.builder()
                .nome("usuario")
                .senha("senha")
                .email("usuario@email.com").build();
    }

    @Test
    @DisplayName("Deve verificar a existencia de um email")
    void verificarEmail() {

        Usuario usuario = criarUsuario();

        entityManager.persist(usuario);

        boolean result = usuarioRepository.existsByEmail("usuario@email.com");

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não houver usuario cadastrado com o email")
    void retornoFalsoUsuarioEmail() {

        boolean result = usuarioRepository.existsByEmail("usuario@email.com");

        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve persistir um usuario da base de dados")
    void persistirUsuarioBd() {

        Usuario usuario = criarUsuario();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve buscar um usuario por email")
    void buscarUsuarioEmail() {

        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        Optional<Usuario> result = usuarioRepository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar usuario por email quando nao existe na base")
    void buscarUsuarioEmailVazio() {

        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        Optional<Usuario> result = usuarioRepository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent()).isTrue();
    }

}