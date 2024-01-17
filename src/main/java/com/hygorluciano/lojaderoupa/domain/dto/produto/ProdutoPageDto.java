package com.hygorluciano.lojaderoupa.domain.dto.produto;

import java.util.List;

public record ProdutoPageDto(
        List<VizualizarProdutoDto> produto,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
