package com.hygorluciano.lojaderoupa.domain.dto.categoria;

import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;

import java.util.List;

public record VerCategoriaComListProdutoDto(
        Long id,
        String nomeCategoria,
        List<VizualizarProdutoDto> produtos_vinculados
) {
}
