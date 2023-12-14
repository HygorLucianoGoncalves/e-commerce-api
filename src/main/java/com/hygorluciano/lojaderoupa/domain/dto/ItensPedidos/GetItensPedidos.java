package com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos;

public record GetItensPedidos(
        Long id_itens,
        Long id_pedido,
        String nome_prodtuto,
        Double preco_unitario,
        Double sub_total,
        Integer quantidade
) {
}
