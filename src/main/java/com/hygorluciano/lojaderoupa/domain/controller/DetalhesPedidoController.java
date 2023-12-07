package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.detalhespedido.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.service.DetalhesPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("detalhespedido")
public class DetalhesPedidoController {

    @Autowired
    DetalhesPedidoService detalhesPedidoService;
    @PostMapping
    public ResponseEntity<HttpStatus> addItensNoPedido(@RequestBody @Valid AddProdutoDto dto){
        return detalhesPedidoService.addProduto(dto);
    }

}
