package com.hygorluciano.lojaderoupa.domain.dto.produto;

public record VizualizarProdutoDto(
        Long id,
        String nome,
        String imagens,
        String categoria,
        Double valor,
        Integer estoque
) {
}
