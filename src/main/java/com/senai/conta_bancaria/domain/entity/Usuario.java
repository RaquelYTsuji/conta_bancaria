package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false, unique = true, length = 11)
    protected String cpf;

    @Column(nullable = false)
    protected String senha;


}
