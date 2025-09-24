package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.*;

import java.math.BigDecimal;

public record ContaResumoDTO(
        TipoConta tipoConta,
        String numero,
        BigDecimal saldo
) {
    public Conta toEntity(Cliente cliente) {
        if(tipoConta == TipoConta.CONTA_CORRENTE){
            return ContaCorrente.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .cliente(cliente)
                    .build();
        } else if(tipoConta == TipoConta.CONTA_POUPANCA){
            return ContaPoupanca.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .cliente(cliente)
                    .build();
        }
        return null;
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getTipo(),
                conta.getNumero(),
                conta.getSaldo()
        );
    }
}
