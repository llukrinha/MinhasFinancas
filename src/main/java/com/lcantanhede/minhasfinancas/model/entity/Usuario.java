package com.lcantanhede.minhasfinancas.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name = "usuario", schema = "financas")
@Data
public class Usuario {

    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (name = "nome")
    @NotBlank
    @Size(max = 255)
    private String nome;

    @Column (name = "email")
    @NotBlank
    @Email
    private String email;

    @Column (name = "senha")
    @NotBlank
    @Size(min = 6)
    private String senha;
}
