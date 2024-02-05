package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import jakarta.validation.constraints.Email;

public record AtualizadoUsuarioDto (String primeiro_nome,
                                    String sobrenome,
                                    @Email
                                     String email,
                                    String senha){

}
