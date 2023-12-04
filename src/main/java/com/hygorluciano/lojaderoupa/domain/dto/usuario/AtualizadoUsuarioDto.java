package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import jakarta.validation.constraints.Email;

public record AtualizadoUsuarioDto (String nome,
                                    @Email
                                     String email,
                                    String senha){

}
