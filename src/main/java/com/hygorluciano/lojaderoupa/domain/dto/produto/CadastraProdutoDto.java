package com.hygorluciano.lojaderoupa.domain.dto.produto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CadastraProdutoDto(
        @NotEmpty(message = "Nome não pode esta em branco")
        String nome,
        String imagens,
        @NotNull(message = "Valot não pode esta vazio")
        Double valor,
        @NotNull(message = "Estoque não pode esta vazio")
        Integer estoque
) {
}
