package com.senai.conta_bancaria.domain.exceptions;

public class BoletoPagoException extends RuntimeException {
    public BoletoPagoException() {
        super("Boleto já foi pago");
    }
}
