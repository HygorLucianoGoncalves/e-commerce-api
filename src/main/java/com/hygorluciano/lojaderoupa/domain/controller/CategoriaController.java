package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
public class CategoriaController{

    @Autowired
    CategoriaService categoriaService;
    @PostMapping
    public ResponseEntity<HttpStatus> criaCategoria(@RequestBody  @Valid CadastraCategoriaDto cadastraCategoriaDto){
        return categoriaService.criaCategoria(cadastraCategoriaDto);
    }
    @GetMapping
    public ResponseEntity<?> verCategoria(Pageable pageable){
        return categoriaService.verCatetoria(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verCategoria(@PathVariable Long id){
        return categoriaService.varCategoriaComListProduto(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> atulizarCategoria(@PathVariable Long id, @RequestBody @Valid CadastraCategoriaDto dto){
        return categoriaService.atualizarCategoria(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaCategoria(@PathVariable Long id){
        return categoriaService.deletaCategoria(id);
    }
}
