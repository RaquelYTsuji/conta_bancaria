package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.TipoConta;

import java.math.BigDecimal;

public record ContaDTO(
        TipoConta tipoConta,
        String id,
        String numero,
        BigDecimal saldo,
        String clienteId,
        Double limite,
        Double taxa,
        Double rendimento
) {
    public static ContaDTO fromEntity(Conta conta) {
        if (conta == null) return null;
        return new ContaDTO(
                null,
                conta.getId(),
                conta.getNumero(),
                conta.getSaldo(),
                conta.getCliente() != null ? conta.getCliente().getId() : null,
                null,
                null,
                null
        );
    }

//    public Conta toEntity(Cliente cliente) {
//        Conta conta = new Conta();
//        conta.setSaldo(this.saldo);
//        conta.setCliente(cliente);
//        return conta;
//    }
}
