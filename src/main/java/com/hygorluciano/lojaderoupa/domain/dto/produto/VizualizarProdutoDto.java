package com.hygorluciano.lojaderoupa.domain.dto.produto;

public record VizualizarProdutoDto(
        String nome,
        String imagens,
        Double valor,
        Integer estoque
) {
}
