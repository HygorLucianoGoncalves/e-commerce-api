package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;
    @PostMapping
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid CadastraProdutoDto dto){
       return produtoService.cadastraProduto(dto);
    }
    @GetMapping
    public ResponseEntity<?> verProdutos(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                         @RequestParam(defaultValue = "10") @Positive @Max(100) int size){
        return produtoService.vizualizarProdutoDto(page,size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody AtualizarProdutoDto dto){
        return produtoService.atualizarProduto(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletaProduto(@PathVariable Long id){
        return produtoService.deletaProduto(id);
    }
}
