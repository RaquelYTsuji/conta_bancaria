package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta{
    @Column(precision = 19, scale = 2)
    private BigDecimal limite = new BigDecimal("5000.000");

    @Column(precision = 19, scale = 4)
    private BigDecimal taxa = new BigDecimal("0.05");

    @Override
    public TipoConta getTipo() {
        return TipoConta.CONTA_CORRENTE;
    }

    @Override
    public void sacar(BigDecimal valor){
        validarValorMaiorQueZero(valor);
        BigDecimal custoSaque = valor.multiply(taxa);
        BigDecimal totalSaque = valor.add(custoSaque);

        if (this.getSaldo().add(this.limite).compareTo(totalSaque) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para o saque");
        }

        this.setSaldo(this.getSaldo().subtract(totalSaque));
    }
}
