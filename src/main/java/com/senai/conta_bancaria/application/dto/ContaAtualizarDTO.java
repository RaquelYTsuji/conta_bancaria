package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaAtualizarDTO(
        @NotNull
        @NotBlank
        @Schema(description = "Tipo de conta", example = "CONTA_CORRENTE")
        TipoConta tipoConta,
        @NotNull
        @NotBlank
        @Schema(description = "Numero da conta", example = "1234-5")
        @Size(max = 20)
        String numero,
        @NotNull
        @NotBlank
        @Schema(description = "Saldo da conta", example = "123")
        BigDecimal saldo,
        @Schema(description = "Limite da conta corrente", example = "123")
        BigDecimal limite,
        @Schema(description = "Taxa da conta corrente", example = "123")
        BigDecimal taxa,
        @Schema(description = "Rendimento da conta poupanca", example = "123")
        BigDecimal rendimento
) {
}
