package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;

public record ContaCorrenteDTO(
        String id,
        Integer numero,
        Double saldo,
        Cliente cliente,
        Double limite,
        Double taxa
) {
    public static ContaCorrenteDTO fromEntity(ContaCorrente contaCorrente) {
        if (contaCorrente == null) return null;
        return new ContaCorrenteDTO(
                contaCorrente.getId(),
                contaCorrente.getNumero(),
                contaCorrente.getSaldo(),
                contaCorrente.getCliente() != null ? contaCorrente.getCliente() : null,
                contaCorrente.getLimite(),
                contaCorrente.getTaxa()
        );
    }

    public ContaCorrente toEntity(Cliente cliente) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setNumero(this.numero);
        contaCorrente.setSaldo(this.saldo);
        contaCorrente.setCliente(cliente);
        contaCorrente.setLimite(this.limite);
        contaCorrente.setTaxa(this.taxa);
        return contaCorrente;
    }
}
