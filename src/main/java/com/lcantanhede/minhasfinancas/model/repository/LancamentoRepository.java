package com.lcantanhede.minhasfinancas.model.repository;

import com.lcantanhede.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
