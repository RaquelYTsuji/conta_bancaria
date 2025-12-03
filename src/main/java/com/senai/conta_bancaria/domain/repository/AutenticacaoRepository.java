package com.senai.conta_bancaria.domain.repository;

import com.senai.conta_bancaria.domain.entity.Autenticacao;
import com.senai.conta_bancaria.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacaoRepository extends JpaRepository<Autenticacao, String> {
    Autenticacao findByCliente(Cliente cliente);
}
