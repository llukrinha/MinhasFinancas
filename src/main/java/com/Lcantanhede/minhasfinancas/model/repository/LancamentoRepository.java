package com.Lcantanhede.minhasfinancas.model.repository;

import com.Lcantanhede.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
