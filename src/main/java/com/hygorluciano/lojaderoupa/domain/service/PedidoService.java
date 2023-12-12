package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosComListItensDto;
import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosDto;
import lombok.Lombok;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {

    ResponseEntity<HttpStatus> criaPedido(String id);

    ResponseEntity<List<VizualizarPedidosDto>> verPeidos();

    ResponseEntity<List<VizualizarPedidosComListItensDto>> verPedidoComItens(Long idPedido);

    ResponseEntity<HttpStatus> confirmarPedido(Long id);

    ResponseEntity<HttpStatus> cancelarPediddo(Long id);
}
