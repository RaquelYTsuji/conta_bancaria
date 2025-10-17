package com.senai.conta_bancaria.infrastructure.security;

import com.senai.conta_bancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws EntidadeNaoEncontradaException {
        var usuario = repository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("usuário"));

        return new User(
                usuario.getCpf(),
                usuario.getSenha(),
                List.of(new SimpleGrantedAuthority("TIPO_USUARIO_" + usuario.getTipo()))
        );
    }
}
