package com.lcantanhede.minhasfinancas.model.repository;

import com.lcantanhede.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento>findByDescricaoIgnoreCaseContaining(String descricao);
}
