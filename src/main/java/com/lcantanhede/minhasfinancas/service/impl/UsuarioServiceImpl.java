package com.lcantanhede.minhasfinancas.service.impl;

import com.lcantanhede.minhasfinancas.exception.RegraNegocioException;
import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import com.lcantanhede.minhasfinancas.model.repository.UsuarioRepository;
import com.lcantanhede.minhasfinancas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }
}
