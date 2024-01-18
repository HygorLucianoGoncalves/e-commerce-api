package com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos;

import java.util.List;

public record ItensPedidoPageDto(

        List<GetItensPedidos> itens_Pedidos,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
