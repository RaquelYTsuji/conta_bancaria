package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public record PagamentoDTO(
        @Schema(description = "Conta para pagamento", example = "Nome")
        Conta conta,
        @NotNull
        @NotBlank
        @Size(min = 11, max = 11)
        @Schema(description = "Boleto a ser pago", example = "12345678910")
        String boleto,
        @Schema(description = "Valor pago", example = "123")
        BigDecimal valorPago,
        @Schema(description = "Data do pagamento")
        LocalDateTime dataPagamento,
        @NotNull
        @NotBlank
        @Schema(description = "Status do pagamento", example = "SUCESSO")
        StatusPagamento status,
        @NotNull
        @Schema(description = "Taxas do pagamento")
        Set<TaxaDTO> taxas
) {
//    public Pagamento toEntity() {
//        return Pagamento.builder()
//                .conta(this.conta)
//                .boleto(this.boleto)
//                .valorPago(this.valorPago)
//                .dataPagamento()
//                .ativo(true)
//                .build();
//    }
//
//    public static ClienteResponseDTO fromEntity(Cliente cliente) {
//            List<ContaResumoDTO> contas = cliente.getContas().stream()
//                    .map(ContaResumoDTO::fromEntity)
//                    .toList();
//            return new ClienteResponseDTO(
//                    cliente.getId(),
//                    cliente.getNome(),
//                    cliente.getCpf(),
//                    contas
//            );
//    }
}
