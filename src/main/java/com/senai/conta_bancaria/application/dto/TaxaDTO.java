package com.senai.conta_bancaria.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TaxaDTO(
        @NotNull
        @NotBlank
        @Schema(description = "Descricao da taxa", example = "Descricao")
        String descricao,
        @NotNull
        @NotBlank
        @Schema(description = "Percentual da taxa", example = "0.1")
        BigDecimal percentual,
        @NotNull
        @NotBlank
        @Schema(description = "Valor fixo da taxa", example = "123")
        BigDecimal valorFixo
) {
}
