package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> verPeididos(){
        return pedidoService.verPeidos();
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
