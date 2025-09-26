package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ContaAtualizarDTO;
import com.senai.conta_bancaria.application.dto.ContaResumoDTO;
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

//    @PutMapping("/depositar/{id}")
//    public ContaDTO depositar(@PathVariable String id, @RequestBody ValorDTO dto) {
//        return contaService.depositar(id, dto);
//    }
//
//    @PutMapping("/sacar/{numero}/{valor}")
//    public ContaResumoDTO sacar(@PathVariable String numero, @PathVariable BigDecimal valor) {
//        return contaService.sacar(numero, valor);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletarConta(@PathVariable String id) {
//        contaService.deletarConta(id);
//    }
}
