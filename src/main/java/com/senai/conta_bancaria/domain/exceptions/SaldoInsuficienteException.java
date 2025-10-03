package com.senai.conta_bancaria.domain.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente para a operação");
    }
}
