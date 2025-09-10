package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO salvarCliente(ClienteDTO dto) {
        Cliente salvo = clienteRepository.save(dto.toEntity());
        return ClienteDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return ClienteDTO.fromEntity(cliente);
    }

    public ClienteDTO atualizarCliente(String id, ClienteDTO dto) {
        Cliente existente = clienteRepository.findById(id).orElse(null);

        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setContas(dto.contas());

        Cliente atualizado = clienteRepository.save(existente);
        return ClienteDTO.fromEntity(atualizado);
    }

    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
