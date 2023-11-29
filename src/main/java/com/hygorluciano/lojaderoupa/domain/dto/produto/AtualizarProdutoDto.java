package com.hygorluciano.lojaderoupa.domain.dto.produto;

import com.hygorluciano.lojaderoupa.domain.model.enums.Categoria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AtualizarProdutoDto(
        String nome,
        Categoria categoria,
        String imagens,
        Double valor,
        Integer estoque
) {
}
