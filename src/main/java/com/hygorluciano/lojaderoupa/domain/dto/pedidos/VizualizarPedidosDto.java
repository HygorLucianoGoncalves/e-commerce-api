package com.hygorluciano.lojaderoupa.domain.dto.pedidos;

import com.hygorluciano.lojaderoupa.domain.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record VizualizarPedidosDto(
        Long id,
        LocalDate data_do_pedido,
        Status status,
        Double total,
        String nome_usuario


) {
}
