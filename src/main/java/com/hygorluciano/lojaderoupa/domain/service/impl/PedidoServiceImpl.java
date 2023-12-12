package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.detalhespedido.VerDetalhesPedido;
import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosComListItensDto;
import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosDto;
import com.hygorluciano.lojaderoupa.domain.model.DetalhesPedido;
import com.hygorluciano.lojaderoupa.domain.model.Pedido;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.model.enums.Status;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import com.hygorluciano.lojaderoupa.domain.service.PedidoService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.pedidos.ValidarPedido;
import com.hygorluciano.lojaderoupa.domain.service.validacao.usuarios.ValidacaoUsuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Transactional
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    List<ValidacaoUsuario> validarUsuarios;
    @Autowired
    List<ValidarPedido> validarPedidos;

    @Override
    public ResponseEntity<HttpStatus> criaPedido(String id) {
        validarUsuarios.forEach(v -> v.verificarId(id));

        Usuario getUsusario = usuarioRepository.getReferenceById(id);
        Pedido novoPedido = new Pedido(getUsusario);

        pedidoRepository.save(novoPedido);
        log.info(novoPedido.getId() + ": Pedido criado com sucesso ");
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Override
    public ResponseEntity<List<VizualizarPedidosDto>> verPeidos() {
        return ResponseEntity.ok(pedidoRepository.findAll().stream()
                .map(pedido -> new VizualizarPedidosDto(
                        pedido.getId(),
                        pedido.getDataPedido(),
                        pedido.getStatus(),
                        pedido.getTotal(),
                        pedido.getUsuario().getNome()
                )).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<VizualizarPedidosComListItensDto>> verPedidoComItens(Long id) {
        validarPedidos.forEach( validar -> validar.validarid(id));

        Pedido pedido = pedidoRepository.getReferenceById(id);

        List<VerDetalhesPedido> listItens = pedido.getDetalhesPedidoList().stream()
                .map(detalhesPedido -> new VerDetalhesPedido(
                        detalhesPedido.getId(),
                        detalhesPedido.getQuantidade(),
                        detalhesPedido.getPrecoUnitario(),
                        detalhesPedido.getProduto().getNome(),
                        detalhesPedido.getPedido().getId()
                )).toList();

        List<VizualizarPedidosComListItensDto> dtos = List.of(
                new VizualizarPedidosComListItensDto(
                        pedido.getId(),
                        pedido.getDataPedido(),
                        pedido.getStatus(),
                        pedido.getTotal(),
                        pedido.getUsuario().getNome(),
                        listItens
                )
        );

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<HttpStatus> confirmarPedido(Long id) {
        validarPedidos.forEach(v -> v.validarid(id));

        Pedido pedido = pedidoRepository.getReferenceById(id);

        validarPedidos.forEach(v -> v.validarStatus(pedido.getStatus()));

        var lista = pedido.getDetalhesPedidoList();

        double valorTotal = 0;

        for (DetalhesPedido i : lista) {
            Produto produto = i.getProduto();
            int estoque = produto.getEstoque();
            produto.setEstoque(estoque - i.getQuantidade());

            double valorQuantidade = i.getQuantidade() * i.getPrecoUnitario();
            valorTotal += valorQuantidade;

        }
        pedido.setTotal(valorTotal);
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        pedidoRepository.save(pedido);

        log.info("Pedido confirmado com sucesso");
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @Override
    public ResponseEntity<HttpStatus> cancelarPediddo(Long id) {

        validarPedidos.forEach(validarPedido -> validarPedido.validarid(id));

        Pedido pedido = pedidoRepository.getReferenceById(id);

        pedido.setStatus(Status.CANCELADO);

        pedidoRepository.save(pedido);
        log.info("Pedido cancelado com sucesso");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
