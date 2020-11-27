package com.lcantanhede.minhasfinancas.service;

import com.lcantanhede.minhasfinancas.exception.ErroAutenticacao;
import com.lcantanhede.minhasfinancas.exception.RegraNegocioException;
import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import com.lcantanhede.minhasfinancas.model.repository.UsuarioRepository;
import com.lcantanhede.minhasfinancas.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioServiceTest {


    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    @DisplayName("Deve autenticar um usuario com sucesso")
    void autenticarUsuario() {

        String email = "email@email.com";
        String senha = "senha";
        ;

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.autenticar(email, senha);

        org.assertj.core.api.Assertions.assertThat(result).isNotNull();

        Assertions.assertDoesNotThrow(() -> usuarioService.autenticar(email, senha));

    }

    @Test
    @DisplayName("Deve lançar erro quando não encontrar usuario cadastrado com email informado")
    void usuarioNaoEncontrado() {

        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() ->
                usuarioService.autenticar("email@email.com", "senha"));

        assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class)
                .hasMessage("Usuario não encontrado para o email informado");
    }

    @Test
    @DisplayName("Deve lançar um erro quando a senha estiver incorreta")
    void senhaIncorreta() {

        String senha = "senha";

        Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();

        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Throwable exception = catchThrowable(() ->
                usuarioService.autenticar("email@email.com", "123"));

        assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class)
                .hasMessage("Senha inválida");

    }

    @Test
    @DisplayName("Deve validar um email")
    void validarEmail() {

        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> usuarioService.validarEmail("email@email.com"));
    }

    @Test
    @DisplayName("Deve lançar error ao validar email quando existir email cadastrado")
    void validarEmailError() {

        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        Assertions.assertThrows(RegraNegocioException.class, () -> usuarioService.validarEmail("email@email.com"));
    }
}

