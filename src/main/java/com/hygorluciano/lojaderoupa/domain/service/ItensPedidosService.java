package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AddProdutoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ItensPedidosService {
    ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto);

//    ResponseEntity<HttpStatus> ItensPedid
}
