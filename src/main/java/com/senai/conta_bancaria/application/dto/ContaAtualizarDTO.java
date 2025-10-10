package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaAtualizarDTO(
        @NotNull
        @NotBlank
        TipoConta tipoConta,
        @NotNull
        @NotBlank
        @Size(max = 20)
        String numero,
        @NotNull
        @NotBlank
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal taxa,
        BigDecimal rendimento
) {
}
