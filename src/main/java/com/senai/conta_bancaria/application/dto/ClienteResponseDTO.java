package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;

import java.util.List;

public record ClienteResponseDTO(
        String id,
        String nome,
        String cpf,
        List<Conta> contas
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() != null ? cliente.getContas() : List.of()
        );
    }
}
