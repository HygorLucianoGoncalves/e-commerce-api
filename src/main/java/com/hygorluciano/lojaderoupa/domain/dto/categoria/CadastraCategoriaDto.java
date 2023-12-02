package com.hygorluciano.lojaderoupa.domain.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CadastraCategoriaDto(
        @NotBlank(message = "Nome da categoria não pode ser vazio")
        String nome
) {
}
