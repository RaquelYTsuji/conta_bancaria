package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.TipoConta;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public ClienteResponseDTO registrarClienteOuAnexarConta(ClienteRegistroDTO dto) {
        var cliente = clienteRepository.findByCpfAndAtivoTrue(dto.cpf())
                .orElseGet(() -> clienteRepository.save(dto.toEntity()));

        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity();
//        if(){
//            if(dto.contaDTO().tipoConta() == TipoConta.CONTA_CORRENTE){
//                ContaCorrente contaCorrente = new ContaCorrente();
//                contaCorrente.setSaldo(BigDecimal.valueOf(0.0));
//                contaCorrente.setCliente(salvo);
//                contaCorrente.setLimite(dto.limite());
//                contaCorrente.setTaxa(dto.taxa());
//                contaRepository.save(contaCorrente);
//            }
//
//            if(dto.tipoConta() == TipoConta.CONTA_POUPANCA){
//                ContaPoupanca contaPoupanca = new ContaPoupanca();
//                contaPoupanca.setSaldo(0.0);
//                contaPoupanca.setCliente(salvo);
//                contaPoupanca.setRendimento(dto.rendimento());
//                contaRepository.save(contaPoupanca);
//            }
//            return ClienteResponseDTO.fromEntity(salvo);
//        }
          return ClienteResponseDTO.fromEntity(cliente);
    }

//    @Transactional(readOnly = true)
//    public List<ClienteDTO> listarClientes() {
//        return clienteRepository.findAll()
//                .stream()
//                .map(ClienteDTO::fromEntity)
//                .toList();
//    }
//
//    @Transactional(readOnly = true)
//    public ClienteDTO buscarClientePorId(String id) {
//        Cliente cliente = clienteRepository.findById(id).orElse(null);
//        return ClienteDTO.fromEntity(cliente);
//    }
//
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
//
//    public void deletarCliente(String id) {
//        clienteRepository.deleteById(id);
//    }
}
