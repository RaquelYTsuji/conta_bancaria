package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ContaAtualizarDTO;
import com.senai.conta_bancaria.application.dto.ContaResumoDTO;
import com.senai.conta_bancaria.application.dto.TransferenciaDTO;
import com.senai.conta_bancaria.application.dto.ValorSaqueDepositoDTO;
import com.senai.conta_bancaria.application.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarConta() {
        return ResponseEntity.ok(contaService.listarConta());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ContaResumoDTO> buscarConta(@PathVariable String numero) {
        return ResponseEntity.ok(contaService.buscarContaPorNumero(numero));
    }

    @PutMapping("/numero/{numero}")
    public ResponseEntity<ContaResumoDTO> atualizarConta(@PathVariable String numero, @RequestBody ContaAtualizarDTO dto) {
        return ResponseEntity.ok(contaService.atualizarConta(numero, dto));
    }

    @PostMapping("/numero/{numero}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar(@PathVariable String numero, @RequestBody ValorSaqueDepositoDTO valor) {
        return ResponseEntity.ok(contaService.sacar(numero, valor));
    }

    @PostMapping("/numero/{numero}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar(@PathVariable String numero, @RequestBody ValorSaqueDepositoDTO dto) {
        return ResponseEntity.ok(contaService.depositar(numero, dto));
    }

    @PostMapping("/numero/{numero}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numero, @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(contaService.transferir(numero, dto));
    }

    @DeleteMapping("/numero/{numero}")
    public ResponseEntity<Void> desativarConta(@PathVariable String numero) {
        contaService.desativarConta(numero);
        return ResponseEntity.noContent().build();
    }
}
