package com.hygorluciano.lojaderoupa.domain.service.validacao.ItensPedidos;

import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.repository.ItensPedidosRepository;
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
    @Autowired
    ItensPedidosRepository itensPedidosRepository;


    @Override
    public void validarId(Long idProduto,Long idPedido) {
        boolean idV = produtoRepository.existsById(idProduto);
        boolean id2 = pedidoRepository.existsById(idPedido);

        if (!idV || !id2){
            throw new ValorNaoEncontrado("Id informado não existe");
        }
    }

    @Override
    public void validarIdItens(Long idItensPedido) {
        boolean id = itensPedidosRepository.existsById(idItensPedido);

        if (!id) throw new ValorNaoEncontrado("Id do ItensPedidos não encontrado ");
    }
}
