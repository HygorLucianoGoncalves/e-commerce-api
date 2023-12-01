package com.hygorluciano.lojaderoupa.domain.dto.produto;

import com.hygorluciano.lojaderoupa.domain.model.Categoria;

public record AtualizarProdutoDto(
        String nome,
        Categoria categoria,
        String imagens,
        Double valor,
        Integer estoque
) {
}
