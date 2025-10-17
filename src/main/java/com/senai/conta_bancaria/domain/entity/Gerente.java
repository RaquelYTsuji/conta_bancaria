package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("ADMIN")
public class Gerente extends Usuario{

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.ADMIN;
    }
}
