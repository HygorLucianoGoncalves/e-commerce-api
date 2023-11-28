package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {
    ResponseEntity<HttpStatus> cadastraProduto(CadastraProdutoDto dto);
    ResponseEntity<List<VizualizarProdutoDto>> vizualizarProdutoDto();
    ResponseEntity<HttpStatus> atualizarProduto(Long id,CadastraProdutoDto dto);
    ResponseEntity<HttpStatus> deletaProduto(Long id);
}
