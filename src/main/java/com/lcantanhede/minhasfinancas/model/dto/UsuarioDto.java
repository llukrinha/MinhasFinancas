package com.lcantanhede.minhasfinancas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", schema = "financas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    @Column(name = "id")
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

    @Column(name = "senha")
    @NotBlank
    private String senha;
}
