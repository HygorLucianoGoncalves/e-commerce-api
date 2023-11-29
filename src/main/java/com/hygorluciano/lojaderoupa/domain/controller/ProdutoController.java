package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.service.ProdutoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoServiceImpl produtoService;
    @PostMapping
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid CadastraProdutoDto dto){
       return produtoService.cadastraProduto(dto);
    }
    @GetMapping
    public ResponseEntity<?> verProdutos(){
        return produtoService.vizualizarProdutoDto();
    }
}
