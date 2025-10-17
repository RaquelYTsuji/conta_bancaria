package com.senai.conta_bancaria.application.dto;

public class AuthDTO {

    public record LoginRequest(
            String cpf,
            String senha
    ) {}
    public record TokenResponse(
            String token
    ) {}
}