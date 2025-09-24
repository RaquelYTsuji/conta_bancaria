package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO registrarClienteOuAnexarConta(ClienteRegistroDTO dto) {
        var cliente = clienteRepository.findByCpfAndAtivoTrue(dto.cpf())
                .orElseGet(() -> clienteRepository.save(dto.toEntity()));

        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean temTipo = contas.stream().anyMatch(c -> c.getClass()
                .equals(novaConta.getClass()) && c.isAtiva());

        if(temTipo)
            throw new RuntimeException("Cliente já possui uma conta ativa deste tipo");

        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(clienteRepository.save(cliente));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarClientesAtivos() {
        return clienteRepository.findAllByAtivoTrue()
                .stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarClientePorId(String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return ClienteResponseDTO.fromEntity(cliente);
    }

//    public ClienteDTO atualizarCliente(String id, ClienteDTO dto) {
//        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
//        if (clienteOptional.isEmpty()) return null;
//
//        Cliente existente = clienteOptional.get();
//        existente.setNome(dto.nome());
//        existente.setCpf(dto.cpf());
//        existente.setContas(dto.contas());
//
//        Cliente atualizado = clienteRepository.save(existente);
//        return ClienteDTO.fromEntity(atualizado);
//    }

    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
