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
    public ClienteResponseDTO buscarClienteAtivoPorCpf(String cpf) {
        Cliente cliente = buscarClientePorCpfEAtivoTrue(cpf);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteRegistroDTO dto) {
        Cliente cliente = buscarClientePorCpfEAtivoTrue(cpf);

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        Cliente atualizado = clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(atualizado);
    }

    public void desativarCliente(String cpf) {
        Cliente cliente = buscarClientePorCpfEAtivoTrue(cpf);

        cliente.setAtivo(false);
        cliente.getContas().forEach(conta -> conta.setAtiva(false));
        clienteRepository.save(cliente);
    }

    private Cliente buscarClientePorCpfEAtivoTrue(String cpf){
        return clienteRepository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado"));
    }
}
