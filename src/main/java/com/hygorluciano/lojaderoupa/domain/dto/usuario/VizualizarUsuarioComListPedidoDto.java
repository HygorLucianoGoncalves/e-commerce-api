package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosDto;

import java.util.List;

public record VizualizarUsuarioComListPedidoDto(
        String id,
        String primeiro_nome,
        String sobrenome,
        String email,
        List<VizualizarPedidosDto> pedidos_do_usuario
) {
}
