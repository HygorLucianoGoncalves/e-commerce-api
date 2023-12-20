package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AtualizarItensPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.GetItensPedidos;
import com.hygorluciano.lojaderoupa.domain.model.ItensPedidos;
import com.hygorluciano.lojaderoupa.domain.model.Pedido;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.ItensPedidosRepository;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.service.ItensPedidosService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.ItensPedidos.ValidarItensPedidos;
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
public class ItensPedidosImpl implements ItensPedidosService {
    final
    PedidoRepository pedidoRepository;
    final
    ProdutoRepository produtoRepository;
    final
    ItensPedidosRepository itensPedidosRepository;
    final
    List<ValidarItensPedidos> validarItensPedidosList;

    @Autowired
    public ItensPedidosImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ItensPedidosRepository itensPedidosRepository, List<ValidarItensPedidos> validarItensPedidosList) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itensPedidosRepository = itensPedidosRepository;
        this.validarItensPedidosList = validarItensPedidosList;
    }

    @Override
    public ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto) {
        validarItensPedidosList.forEach(validar -> validar.validarId(dto.idProduto(), dto.idPedido()));

        Produto produto = produtoRepository.getReferenceById(dto.idProduto());
        Pedido pedido = pedidoRepository.getReferenceById(dto.idPedido());

        com.hygorluciano.lojaderoupa.domain.model.ItensPedidos novoItens = new com.hygorluciano.lojaderoupa.domain.model.ItensPedidos(produto, pedido, dto.quantidade());

        itensPedidosRepository.save(novoItens);

        log.info("Produto add com sucesso no Detalhes Pedidos");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<GetItensPedidos>> verItensPedido() {
        List<GetItensPedidos> itensPedidos = itensPedidosRepository.findAll().stream().map(
                itensPedidos1 -> new GetItensPedidos(
                        itensPedidos1.getId(),
                        itensPedidos1.getPedido().getId(),
                        itensPedidos1.getProduto().getNome(),
                        itensPedidos1.getPrecoUnitario(),
                        itensPedidos1.getSubTotal(),
                        itensPedidos1.getQuantidade()
                )).toList();

        log.info("Get ItensPedidos vou aberto ");

        return ResponseEntity.ok(itensPedidos);
    }

    @Override
    public ResponseEntity<HttpStatus> atualizarQuantidadeItensPedidos(Long idItensPedido, AtualizarItensPedidosDto dto) {
        validarItensPedidosList.forEach(v -> v.validarIdItens(idItensPedido));

        ItensPedidos itensPedidos = itensPedidosRepository.getReferenceById(idItensPedido);

        itensPedidos.setQuantidade(dto.quantidade() == null ? itensPedidos.getQuantidade() : dto.quantidade());
        itensPedidos.setSubTotal(itensPedidos.getQuantidade() * itensPedidos.getPrecoUnitario());

        itensPedidosRepository.save(itensPedidos);

        log.info("Atualizado a quantidade do Itens Com ID {}",idItensPedido);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<HttpStatus> deleteItensPedido(Long idItensPedidos) {
        validarItensPedidosList.forEach(v -> v.validarIdItens(idItensPedidos));

        itensPedidosRepository.deleteById(idItensPedidos);

        log.info("Itens Pedidos deletados com sucesso.  id do itens {}", idItensPedidos);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
