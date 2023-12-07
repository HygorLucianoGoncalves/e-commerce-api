package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.detalhespedido.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.DetalhesPedido;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface DetalhesPedidoService {
    ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto);
}
