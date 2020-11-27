package com.lcantanhede.minhasfinancas.service;

import com.lcantanhede.minhasfinancas.exception.RegraNegocioException;
import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import com.lcantanhede.minhasfinancas.model.repository.UsuarioRepository;
import com.lcantanhede.minhasfinancas.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioServiceTest {

    UsuarioService usuarioService;
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp(){
        usuarioRepository= Mockito.mock(UsuarioRepository.class);
        usuarioService=new UsuarioServiceImpl(usuarioRepository);
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

