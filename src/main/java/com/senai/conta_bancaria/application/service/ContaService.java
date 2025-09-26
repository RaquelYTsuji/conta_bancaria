package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaAtualizarDTO;
import com.senai.conta_bancaria.application.dto.ContaResumoDTO;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.entity.TipoConta;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarConta() {
        return contaRepository.findAllByAtivaTrue()
                .stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarContaPorNumero(String numero) {
        Conta conta = buscarContaPorNumeroEAtivoTrue(numero);
        return ContaResumoDTO.fromEntity(conta);
    }

    public ContaResumoDTO atualizarConta(String numero, ContaAtualizarDTO dto) {
        var conta = contaRepository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("Conta não encontrada"));

        conta.setSaldo(dto.saldo());

        if (conta instanceof ContaCorrente contaCorrente &&
                dto.tipoConta() == TipoConta.CONTA_CORRENTE) {
            contaCorrente.setLimite(dto.limite());
            contaCorrente.setTaxa(dto.taxa());
        } else if (conta instanceof ContaPoupanca contaPoupanca &&
                dto.tipoConta() == TipoConta.CONTA_POUPANCA) {
            contaPoupanca.setRendimento(dto.rendimento());
        }

        return ContaResumoDTO.fromEntity(contaRepository.save(conta));
    }
//
//    public ContaDTO depositar(String id, ValorDTO dto){
//        Optional<Conta> contaOptional = contaRepository.findById(id);
//        if (contaOptional.isEmpty()) return null;
//
//        Conta existente = contaOptional.get();
//        existente.setSaldo(existente.getSaldo() + dto.valor());
//
//        return ContaDTO.fromEntity(existente);
//    }
//
//    public ContaResumoDTO sacar(String numero, BigDecimal valor){
//        Conta conta = buscarContaPorNumeroEAtivoTrue(numero);
//        conta.setSaldo(conta.getSaldo().subtract(valor));
//
//        return ContaResumoDTO.fromEntity(conta);
//    }
//
//    public void deletarConta(String id) {
//        contaRepository.deleteById(id);
//    }

    private Conta buscarContaPorNumeroEAtivoTrue(String numero){
        return contaRepository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("Conta não encontrada"));
    }
}
