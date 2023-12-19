package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AddProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.AtualizarItensPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.ItensPedidos.GetItensPedidos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItensPedidosService {
    ResponseEntity<HttpStatus> addProduto(AddProdutoDto dto);

    ResponseEntity<List<GetItensPedidos>> verItensPedido();

    public ResponseEntity<HttpStatus> atualizarQuantidadeItensPedidos(Long idItensPedido, AtualizarItensPedidosDto dto);
}
