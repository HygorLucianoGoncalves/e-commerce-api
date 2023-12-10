package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import com.hygorluciano.lojaderoupa.domain.dto.Pedidos.VizualizarPedidosDto;

import java.util.List;

public record VizualizarUsuarioComListPedidoDto(
        String id,
        String nome,
        String email,
        List<VizualizarPedidosDto> pedidos_do_usuario
) {
}
