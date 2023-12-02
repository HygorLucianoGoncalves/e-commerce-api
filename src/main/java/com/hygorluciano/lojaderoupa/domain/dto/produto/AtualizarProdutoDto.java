package com.hygorluciano.lojaderoupa.domain.dto.produto;

import com.hygorluciano.lojaderoupa.domain.model.Categoria;

public record AtualizarProdutoDto(
        String nome,
        Long categoria_id,
        String imagens,
        Double valor,
        Integer estoque
) {
}
