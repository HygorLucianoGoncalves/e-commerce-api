package com.hygorluciano.lojaderoupa.domain.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull
                @Email
        String email,
        @NotBlank
        String senha
) {
}
