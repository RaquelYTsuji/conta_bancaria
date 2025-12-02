package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.domain.repository.DispositivoIoTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DispositivoIoTService {
    private final DispositivoIoTRepository dispositivoIoTRepository;
//
//    public TaxaDTO criarTaxa(TaxaDTO dto) {
//        return TaxaDTO.fromEntity(taxaRepository.save(dto.toEntity()));
//    }
//
//    @Transactional(readOnly = true)
//    public List<TaxaDTO> listarTaxas() {
//        return taxaRepository.findAll()
//                .stream()
//                .map(TaxaDTO::fromEntity)
//                .toList();
//    }
//
//    @Transactional(readOnly = true)
//    public TaxaDTO buscarTaxaPorId(String id) {
//        return TaxaDTO.fromEntity(taxaRepository.findById(id).orElseThrow(
//                () -> new EntidadeNaoEncontradaException("Taxa")));
//    }
//
//    public TaxaDTO atualizarTaxa(String id, TaxaDTO dto) {
//        Taxa taxa = taxaRepository.findById(id).orElseThrow(
//                () -> new EntidadeNaoEncontradaException("Taxa"));
//        taxa.setDescricao(dto.descricao());
//        taxa.setPercentual(dto.percentual());
//        taxa.setValorFixo(dto.valorFixo());
//        return TaxaDTO.fromEntity(taxaRepository.save(taxa));
//    }
//
//    public void deletarTaxa(String id) {
//        Taxa taxa = taxaRepository.findById(id).orElseThrow(
//                () -> new EntidadeNaoEncontradaException("Taxa"));
//        taxaRepository.delete(taxa);
//    }
}
