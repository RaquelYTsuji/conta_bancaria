package com.senai.conta_bancaria.domain.exceptions;

public class TipoDeContaInvalidaException extends RuntimeException {
    public TipoDeContaInvalidaException() {
        super("Tipo de conta inválida. Os tipos válidos são: 'CONTA_CORRENTE' e 'CONTA_POUPANCA'");
    }
}
