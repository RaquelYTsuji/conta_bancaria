package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.entity.TipoConta;

import java.math.BigDecimal;

public record ContaResumoDTO(
        TipoConta tipoConta,
        String numero,
        BigDecimal saldo
) {
    public Conta toEntity() {
        if(tipoConta == TipoConta.CONTA_CORRENTE){
            return ContaCorrente.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .build();
        } else if(tipoConta == TipoConta.CONTA_POUPANCA){
            return ContaPoupanca.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .build();
        }
        return null
    }
}
