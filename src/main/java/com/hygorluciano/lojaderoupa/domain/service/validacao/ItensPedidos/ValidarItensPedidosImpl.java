package com.hygorluciano.lojaderoupa.domain.service.validacao.ItensPedidos;

import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidarItensPedidosImpl implements ValidarItensPedidos {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public void validarId(Long idProduto,Long idPedido) {
        boolean idV = produtoRepository.existsById(idProduto);
        boolean id2 = pedidoRepository.existsById(idPedido);

        if (!idV || !id2){
            throw new ValorNaoEncontrado("Id informado não existe");
        }
    }
}
