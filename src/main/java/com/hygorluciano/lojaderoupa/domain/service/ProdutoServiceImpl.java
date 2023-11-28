package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.service.validacao.produto.ValidacaoProduto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Transactional
@Slf4j
public class ProdutoServiceImpl implements ProdutoService{
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    List<ValidacaoProduto> validacaoProdutos;
    @Override
    public ResponseEntity<HttpStatus> cadastraProduto(CadastraProdutoDto dto) {
        validacaoProdutos.forEach(v -> v.validarNomeProduto(dto));
        log.info("passou da validacao");

        Produto novoProduto = new Produto(dto);
        produtoRepository.save(novoProduto);

        log.info("Produto cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<VizualizarProdutoDto>> vizualizarProdutoDto() {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> atualizarProduto(Long id, CadastraProdutoDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deletaProduto(Long id) {
        return null;
    }
}
