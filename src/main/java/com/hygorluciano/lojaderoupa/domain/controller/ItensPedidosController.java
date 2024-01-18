package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AtualizarItensPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.ItensPedidoPageDto;
import com.hygorluciano.lojaderoupa.domain.service.ItensPedidosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("detalhespedido")
public class ItensPedidosController {

    @Autowired
    ItensPedidosService itensPedidosService;
    @PostMapping
    public ResponseEntity<HttpStatus> addItensNoPedido(@RequestBody @Valid AddProdutoDto dto){
        return itensPedidosService.addProduto(dto);
    }

    @GetMapping
    public ResponseEntity<ItensPedidoPageDto> verItensPedidos(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                              @RequestParam(defaultValue = "10") @Positive int size){
        return itensPedidosService.verItensPedido(page,size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarQuantidade(@PathVariable Long id, @RequestBody AtualizarItensPedidosDto dto){
        return itensPedidosService.atualizarQuantidadeItensPedidos(id,dto);
    }

    @DeleteMapping("/{idItensPedido}")
    public ResponseEntity<?> deleteItensPedidos(@PathVariable Long idItensPedido){
        return itensPedidosService.deleteItensPedido(idItensPedido);
    }
}
