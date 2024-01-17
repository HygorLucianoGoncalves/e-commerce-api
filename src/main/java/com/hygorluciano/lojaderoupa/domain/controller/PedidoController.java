package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.pedidos.PedidoPageDto;
import com.hygorluciano.lojaderoupa.domain.service.PedidoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> criaPedido(@PathVariable String id){
        return pedidoService.criaPedido(id);
    }
    @GetMapping
    public ResponseEntity<PedidoPageDto> verPeididos(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                     @RequestParam(defaultValue = "10")  @Positive @Max(100) int size){
        return pedidoService.verPeidos(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verPedidoComListaItens(@PathVariable Long id){
        return pedidoService.verPedidoComItens(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> confirmaPedido(@PathVariable Long id){
        return pedidoService.confirmarPedido(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id){
        return pedidoService.cancelarPediddo(id);
    }

}
