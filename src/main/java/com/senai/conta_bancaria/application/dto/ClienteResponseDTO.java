package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ClienteResponseDTO(
        @Schema(description = "Id do cliente")
        String id,
        @Schema(description = "Nome do cliente")
        String nome,
        @Schema(description = "Cpf do cliente")
        String cpf,
        @Schema(description = "Contas do cliente")
        List<ContaResumoDTO> contas
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                contas
        );
    }
}
