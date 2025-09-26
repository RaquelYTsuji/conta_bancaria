package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta{
    @Column(precision = 10, scale = 4)
    private BigDecimal rendimento;

    @Override
    public TipoConta getTipo() {
        return TipoConta.CONTA_POUPANCA;
    }
}
