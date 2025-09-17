package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta{
    @Column(precision = 19, scale = 2)
    @NotNull(message = "limite não pode ser nulo")
    private BigDecimal limite;

    @Column(precision = 19, scale = 4)
    @NotNull(message = "taxa não pode ser nulo")
    private BigDecimal taxa;
}
