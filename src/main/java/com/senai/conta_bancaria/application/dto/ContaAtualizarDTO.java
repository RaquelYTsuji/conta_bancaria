package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.*;

import java.math.BigDecimal;

public record ContaAtualizarDTO(
        TipoConta tipoConta,
        String numero,
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal taxa,
        BigDecimal rendimento
) {
}
