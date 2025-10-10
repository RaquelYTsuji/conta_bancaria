package com.senai.conta_bancaria.infrastructure.config;

import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrap {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${sistema.admin.email}")
    private String adminEmail;

    @Value("${sistema.admin.senha}")
    private String adminSenha;

    @Override
    public void run(String... args) {
        professorRepository.findByEmail(adminEmail).ifPresentOrElse(
                prof -> {
                    if (!prof.isAtivo()) {
                        prof.setAtivo(true);
                        professorRepository.save(prof);
                    }
                },
                () -> {
                    Professor admin = Professor.builder()
                            .nome("Administrador Provisório")
                            .email(adminEmail)
                            .cpf("000.000.000-00")
                            .senha(passwordEncoder.encode(adminSenha))
                            .role(Role.ADMIN)
                            .build();
                    professorRepository.save(admin);
                    System.out.println("⚡ Usuário admin provisório criado: " + adminEmail);
                }
        );
    }
}
