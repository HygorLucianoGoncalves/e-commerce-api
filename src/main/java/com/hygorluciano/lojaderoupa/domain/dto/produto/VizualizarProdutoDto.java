package com.hygorluciano.lojaderoupa.domain.dto.produto;

public record VizualizarProdutoDto(
        String nome,
        String imagens,
        String categoria,
        Double valor,
        Integer estoque
) {
}
