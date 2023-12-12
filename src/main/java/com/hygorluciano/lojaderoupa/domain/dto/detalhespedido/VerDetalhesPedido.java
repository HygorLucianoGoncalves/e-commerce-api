package com.hygorluciano.lojaderoupa.domain.dto.detalhespedido;

public record VerDetalhesPedido(

        Long id_itens,
        Integer quantidade,
        Double preco_unitario,
        String nome_prodtuto,
        Long id_pedido
) {
}
