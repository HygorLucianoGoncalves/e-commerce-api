package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.Pedidos.VizualizarPedidosDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {

    ResponseEntity<HttpStatus> criaPedido(String id);

    ResponseEntity<List<VizualizarPedidosDto>> verPeidos();
}
