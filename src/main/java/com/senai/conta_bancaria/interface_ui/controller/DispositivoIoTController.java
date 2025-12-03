package com.senai.conta_bancaria.interface_ui.controller;

import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPayload;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPublisher;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttSubscriber;
import com.senai.conta_bancaria.application.dto.DispositivoIoTDTO;
import com.senai.conta_bancaria.application.service.DispositivoIoTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DispositivoIoT", description = "Gerenciamento de dispositivo IoT dos clientes do banco")
@RestController
@RequiredArgsConstructor
public class DispositivoIoTController {
    private final DispositivoIoTService dispositivoIoTService;

    @Operation(
            summary = "Validar com um dispositivo IoT",
            description = "Realiza a validacao pelo dispositivo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = DispositivoIoTDTO.class),
                            examples = @ExampleObject(name = "Exemplo válido", value = """
                                        {
                                            "codigoSerial": "123",
                                            "chavePublica": "123",
                                            "cpfCliente": "12345678910"
                                         }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dispositivo validado com sucesso"),
            }
    )
    @SecurityRequirements
    @PostMapping
    @MqttPublisher("banco/dispositivoIoT")
    public DispositivoIoTDTO validacao(@RequestBody DispositivoIoTDTO dispositivoIoTDTO) {
        return dispositivoIoTService.validacao(dispositivoIoTDTO);
    }

    @MqttSubscriber("banco/dispositivoIoT")
    public void salvar(@MqttPayload DispositivoIoTDTO dispositivoIoTDTO) {
        dispositivoIoTService.salvar(dispositivoIoTDTO);
    }
}
