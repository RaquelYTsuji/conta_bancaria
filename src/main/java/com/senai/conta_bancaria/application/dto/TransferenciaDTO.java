package com.senai.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotNull
        @NotBlank
        @Size(max = 20)
        String numeroContaDestino,
        @NotNull
        BigDecimal valor
){
}
