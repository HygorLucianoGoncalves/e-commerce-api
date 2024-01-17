package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.ProdutoPageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {
    ResponseEntity<HttpStatus> cadastraProduto(CadastraProdutoDto dto);
    ResponseEntity<ProdutoPageDto> vizualizarProdutoDto(int page, int size);
    ResponseEntity<HttpStatus> atualizarProduto(Long id, AtualizarProdutoDto dto);
    ResponseEntity<HttpStatus> deletaProduto(Long id);
}
