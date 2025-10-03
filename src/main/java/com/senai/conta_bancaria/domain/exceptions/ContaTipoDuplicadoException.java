package com.senai.conta_bancaria.domain.exceptions;

public class ContaTipoDuplicadoException extends RuntimeException {
    public ContaTipoDuplicadoException() {
        super("Cliente já possui uma conta ativa deste tipo");
    }
}
