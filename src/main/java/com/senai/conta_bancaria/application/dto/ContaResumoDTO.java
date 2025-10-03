package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.*;
import com.senai.conta_bancaria.domain.exceptions.TipoDeContaInvalidaException;

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
                    .limite(new BigDecimal("500.000"))
                    .taxa(new BigDecimal("0.05"))
                    .build();
        } else if(tipoConta == TipoConta.CONTA_POUPANCA){
            return ContaPoupanca.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .cliente(cliente)
                    .rendimento(new BigDecimal("0.01"))
                    .build();
        }
        throw new TipoDeContaInvalidaException();
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getTipo(),
                conta.getNumero(),
                conta.getSaldo()
        );
    }
}
