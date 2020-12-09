package com.lcantanhede.minhasfinancas.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoDto {

    private Long id;

    private String descricao;

    private Integer mes;

    private Integer ano;

    private BigDecimal valor;

    private Long usuario;

    private String tipo;

    private String status;

    
}
