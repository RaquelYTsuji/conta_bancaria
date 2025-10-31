package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String descricao;

    @Column(precision = 10, scale = 4)
    private BigDecimal percentual;

    @Column(precision = 19, scale = 2)
    private BigDecimal valorFixo;

    @ManyToMany(mappedBy = "taxas")
    private Set<Pagamento> pagamentos;
}
