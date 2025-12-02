package com.senai.conta_bancaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoIoTRepository extends JpaRepository<DispositivoIoTRepository, String> {
}
