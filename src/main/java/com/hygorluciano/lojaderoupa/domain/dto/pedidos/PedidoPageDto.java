package com.hygorluciano.lojaderoupa.domain.dto.pedidos;

import java.util.List;

public record PedidoPageDto(
        List<VizualizarPedidosDto> pedidos,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
