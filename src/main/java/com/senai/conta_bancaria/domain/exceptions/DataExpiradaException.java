package com.senai.conta_bancaria.domain.exceptions;

public class DataExpiradaException extends RuntimeException {
    public DataExpiradaException() {
        super("A data da operação foi expirada");
    }
}
