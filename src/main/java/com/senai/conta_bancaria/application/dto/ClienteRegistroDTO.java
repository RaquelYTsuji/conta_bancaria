package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotNull
        @NotBlank
        @Size(max = 120)
        String nome,
        @NotNull
        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,
        @NotNull
        @NotBlank
        String senha,
        @NotNull
        ContaResumoDTO contaDTO
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .ativo(true)
                .build();
    }
}
