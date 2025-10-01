package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cliente_numero", columnNames = "numero"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})} //cliente nao pode ter duas contas de tipos iguais
)
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "numero não pode ser maior que 20 caracteres")
    @NotBlank(message = "numero não pode ser vazio")
    private String numero;

    @Column(nullable = false, precision = 19, scale = 2) //precision = tamanho, scale = casas decimais
    @NotNull(message = "saldo não pode ser nulo")
    private BigDecimal saldo;

    @Column(nullable = false)
    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    public abstract TipoConta getTipo();

    public void sacar(BigDecimal valor){
        validarValorMaiorQueZero(valor);
        if (this.saldo.compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para o saque");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor){
        validarValorMaiorQueZero(valor);
        this.saldo = this.saldo.add(valor);
    }

    public void transferir(Conta contaDestino, BigDecimal valor){
        if (this.id.equals(contaDestino.getId())){
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }

        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected static void validarValorMaiorQueZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O valor da operacao deve ser maior que zero");
        }
    }
}
