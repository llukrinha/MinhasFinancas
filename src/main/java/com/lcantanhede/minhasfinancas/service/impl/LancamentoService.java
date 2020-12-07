package com.lcantanhede.minhasfinancas.service.impl;

import com.lcantanhede.minhasfinancas.model.entity.Lancamento;
import com.lcantanhede.minhasfinancas.model.enums.StatusLancamento;
import com.lcantanhede.minhasfinancas.model.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Autowired
    @Transactional
    public Lancamento salvar(Lancamento lancamento){
       return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar( Lancamento lancamento){
        return null;
    }

    void deletar(Lancamento lancamento){

    }

    public List<Lancamento> buscar(Lancamento lancamento){
        return null;
    }

    void  atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento){

    }
}
