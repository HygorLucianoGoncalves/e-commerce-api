package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.Pedidos.VizualizarPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.DetalhesPedido;
import com.hygorluciano.lojaderoupa.domain.model.Pedido;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.model.enums.Status;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import com.hygorluciano.lojaderoupa.domain.service.PedidoService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.pedidos.ValidarPedido;
import com.hygorluciano.lojaderoupa.domain.service.validacao.usuarios.ValidacaoUsuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
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
    public ResponseEntity<HttpStatus> confirmarPedido(Long id) {
        validarPedidos.forEach(v -> v.validarid(id));

        Pedido pedido = pedidoRepository.getReferenceById(id);

        var lista = pedido.getDetalhesPedidoList();

        double valorTotal = 0;

        for (DetalhesPedido i : lista){
            double valorQuantidade = i.getQuantidade() * i.getPrecoUnitario();
            valorTotal += valorQuantidade;
        }

        pedido.setTotal(valorTotal);
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    //fazer delete com status do pedido
}
