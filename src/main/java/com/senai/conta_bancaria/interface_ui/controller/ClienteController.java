package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        return clienteService.registrarClienteOuAnexarConta(dto);
    }

//    @GetMapping
//    public List<ClienteDTO> listarCliente() {
//        return clienteService.listarClientes();
//    }
//
//    @GetMapping("/{id}")
//    public ClienteDTO buscarCliente(@PathVariable String id) {
//        return clienteService.buscarClientePorId(id);
//    }
//
//    @PutMapping("/{id}")
//    public ClienteDTO atualizarCliente(@PathVariable String id, @RequestBody ClienteDTO dto) {
//        return clienteService.atualizarCliente(id, dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletarCliente(@PathVariable String id) {
//        clienteService.deletarCliente(id);
//    }
}
