package com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos;

import jakarta.validation.constraints.NotNull;

public record AddProdutoDto(
        @NotNull
        Long idPedido,
        @NotNull
        Long idProduto,
        @NotNull
        Integer quantidade


) {
}
