package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.DispositivoIoT;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DispositivoIoTDTO(
        @NotNull
        @Schema(description = "Codigo serial do dispositivo", example = "123")
        String codigoSerial,
        @NotNull
        @Schema(description = "Chave publica do dispositivo", example = "123")
        String chavePublica,
        @NotNull
        @Schema(description = "Cpf do cliente do dispositivo", example = "12345678910")
        String cpfCliente
) {
        public DispositivoIoT toEntity(Cliente cliente) {
                return DispositivoIoT.builder()
                        .codigoSerial(this.codigoSerial)
                        .chavePublica(this.chavePublica)
                        .cliente(cliente)
                        .ativo(true)
                        .build();
        }

        public static DispositivoIoTDTO fromEntity(DispositivoIoT dispositivoIoT) {
                return new DispositivoIoTDTO(
                        dispositivoIoT.getCodigoSerial(),
                        dispositivoIoT.getChavePublica(),
                        dispositivoIoT.getCliente().getCpf()
                );
        }
}
