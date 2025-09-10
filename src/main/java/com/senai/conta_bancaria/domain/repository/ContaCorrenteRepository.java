package com.senai.conta_bancaria.domain.repository;

import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, String> {
}
