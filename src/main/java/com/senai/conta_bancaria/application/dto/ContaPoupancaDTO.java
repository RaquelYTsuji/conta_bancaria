package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

public record ContaPoupancaDTO(
        String id,
        Integer numero,
        Double saldo,
        Cliente cliente,
        Double rendimento
) {
    public static ContaPoupancaDTO fromEntity(ContaPoupanca contaPoupanca) {
        if (contaPoupanca == null) return null;
        return new ContaPoupancaDTO(
                contaPoupanca.getId(),
                contaPoupanca.getNumero(),
                contaPoupanca.getSaldo(),
                contaPoupanca.getCliente() != null ? contaPoupanca.getCliente() : null,
                contaPoupanca.getRendimento()
        );
    }

    public ContaPoupanca toEntity(Cliente cliente) {
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setNumero(this.numero);
        contaPoupanca.setSaldo(this.saldo);
        contaPoupanca.setCliente(cliente);
        contaPoupanca.setRendimento(this.rendimento);
        return contaPoupanca;
    }
}
