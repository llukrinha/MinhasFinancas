package com.lcantanhede.minhasfinancas.controll;

import com.lcantanhede.minhasfinancas.exception.ErroAutenticacao;
import com.lcantanhede.minhasfinancas.exception.RegraNegocioException;
import com.lcantanhede.minhasfinancas.model.dto.UsuarioDto;
import com.lcantanhede.minhasfinancas.model.entity.Usuario;
import com.lcantanhede.minhasfinancas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody UsuarioDto usuarioDto) {

        Usuario usuario = Usuario.builder()
                .nome(usuarioDto.getNome()).email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha()).build();

        try {
            Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDto usuarioDto) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar(usuarioDto.getEmail(), usuarioDto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);

        } catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
