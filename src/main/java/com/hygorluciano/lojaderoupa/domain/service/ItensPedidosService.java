package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AtualizarItensPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.ItensPedidoPageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ItensPedidosService {
    ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto);

    ResponseEntity<ItensPedidoPageDto> verItensPedido(int page, int size);

    ResponseEntity<HttpStatus> atualizarQuantidadeItensPedidos(Long idItensPedido, AtualizarItensPedidosDto dto);

    ResponseEntity<HttpStatus> deleteItensPedido(Long idItensPedidos);

}
