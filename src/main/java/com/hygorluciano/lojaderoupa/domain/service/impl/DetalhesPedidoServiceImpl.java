package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.detalhespedido.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.DetalhesPedido;
import com.hygorluciano.lojaderoupa.domain.model.Pedido;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.DetalhesPedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.service.DetalhesPedidoService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.detalhespedidos.ValidarDetalhesPedidos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@Transactional
public class DetalhesPedidoServiceImpl implements DetalhesPedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    DetalhesPedidoRepository  detalhesPedidoRepository;
    @Autowired
    List<ValidarDetalhesPedidos> validarDetalhesPedidosList;

    @Override
    public ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto) {
        validarDetalhesPedidosList.forEach(validar -> validar.validarId(dto.idProduto(), dto.idPedido()));

        Produto produto = produtoRepository.getReferenceById(dto.idProduto());
        Pedido pedido = pedidoRepository.getReferenceById(dto.idPedido());

        DetalhesPedido novoItens = new DetalhesPedido(produto,pedido,dto.quantidade());

        detalhesPedidoRepository.save(novoItens);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
