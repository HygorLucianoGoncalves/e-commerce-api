package com.hygorluciano.lojaderoupa.domain.dto.Pedidos;

import com.hygorluciano.lojaderoupa.domain.model.enums.Status;

import java.time.LocalDateTime;

public record VizualizarPedidosDto(
        Long id,
        LocalDateTime data_do_pedido,
        Status status,
        Double total,
        String nome_usuario


) {
}
