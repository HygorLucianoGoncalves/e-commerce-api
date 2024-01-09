package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import com.hygorluciano.lojaderoupa.domain.model.enums.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastraUsuarioDto(
        @NotBlank(message = "Nome não pode esta vazio")
        String nome,
        @NotBlank(message = "Email não pode esta vazio")
                @Email(message = "Email errado")
        String email,
        @NotBlank(message = "Senha não pode ser em branco ")
        String senha
) {
}
