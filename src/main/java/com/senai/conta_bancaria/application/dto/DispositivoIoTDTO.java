package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.DispositivoIoT;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DispositivoIoTDTO(
        @NotNull
        @Schema(description = "Descricao da taxa", example = "IOF")
        String codigoSerial,
        @NotNull
        @Schema(description = "Tipo de pagamentos que usam a taxa", example = "LUZ")
        String chavePublica,
        @NotNull
        @Schema(description = "Percentual da taxa", example = "0.1")
        Cliente cliente
) {
        public DispositivoIoT toEntity() {
                return DispositivoIoT.builder()
                        .codigoSerial(this.codigoSerial)
                        .chavePublica(this.chavePublica)
                        .cliente(this.cliente)
                        .ativo(true)
                        .build();
        }

        public static DispositivoIoTDTO fromEntity(DispositivoIoT dispositivoIoT) {
                return new DispositivoIoTDTO(
                        dispositivoIoT.getCodigoSerial(),
                        dispositivoIoT.getChavePublica(),
                        dispositivoIoT.getCliente()
                );
        }
}
