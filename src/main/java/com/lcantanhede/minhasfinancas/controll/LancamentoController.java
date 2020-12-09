package com.lcantanhede.minhasfinancas.controll;

import com.lcantanhede.minhasfinancas.model.dto.LancamentoDto;
import com.lcantanhede.minhasfinancas.service.LancamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDto lancamentoDto){
    return null;
    }

}
