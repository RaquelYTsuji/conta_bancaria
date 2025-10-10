package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.application.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@Valid  @RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO novoCliente = clienteService.registrarClienteOuAnexarConta(dto);
        return ResponseEntity.created(
                URI.create("/api/cliente/cpf/" + novoCliente.cpf())
        ).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos() {
        return ResponseEntity.ok(clienteService.listarClientesAtivos());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClienteAtivoPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarClienteAtivoPorCpf(cpf));
    }

    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarClientePorCpf(@PathVariable String cpf, @Valid @RequestBody ClienteRegistroDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarCliente(cpf, dto));
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> desativarCliente(@PathVariable String cpf) {
        clienteService.desativarCliente(cpf);
        return ResponseEntity.noContent().build();
    }
}
