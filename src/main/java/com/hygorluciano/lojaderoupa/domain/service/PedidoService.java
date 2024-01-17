package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.pedidos.PedidoPageDto;
import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosComListItensDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {

    ResponseEntity<HttpStatus> criaPedido(String id);

    ResponseEntity<PedidoPageDto> verPeidos( int page,int size);

    ResponseEntity<List<VizualizarPedidosComListItensDto>> verPedidoComItens(Long idPedido);

    ResponseEntity<HttpStatus> confirmarPedido(Long id);

    ResponseEntity<HttpStatus> cancelarPediddo(Long id);
}
