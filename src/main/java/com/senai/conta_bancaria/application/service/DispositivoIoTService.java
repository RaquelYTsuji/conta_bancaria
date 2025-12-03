package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.DispositivoIoTDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.DispositivoIoT;
import com.senai.conta_bancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DispositivoIoTService {
    private final AutenticacaoService autenticacaoService;
    private final ClienteRepository clienteRepository;

    public DispositivoIoTDTO validacao(DispositivoIoTDTO dto) {
        Cliente cLiente = clienteRepository.findByCpfAndAtivoTrue(dto.cpfCliente()).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Conta"));

        DispositivoIoT dispositivoIoT = dto.toEntity(cLiente);

        dispositivoIoT.setCliente(cLiente);

        return DispositivoIoTDTO.fromEntity(dispositivoIoT);
    }

    public void salvar(DispositivoIoTDTO dto) {
        Cliente cLiente = clienteRepository.findByCpfAndAtivoTrue(dto.cpfCliente()).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Conta"));

        autenticacaoService.validar(cLiente);
    }

}
