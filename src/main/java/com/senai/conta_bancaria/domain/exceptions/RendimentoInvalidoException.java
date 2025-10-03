package com.senai.conta_bancaria.domain.exceptions;

public class RendimentoInvalidoException extends RuntimeException {
    public RendimentoInvalidoException() {
        super("Rendimento apenas para a conta poupança");
    }
}
