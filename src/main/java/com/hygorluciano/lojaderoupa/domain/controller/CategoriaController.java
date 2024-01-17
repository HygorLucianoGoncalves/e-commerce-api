package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.CategoriaPageDto;
import com.hygorluciano.lojaderoupa.domain.service.CategoriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
@Validated
public class CategoriaController{

    final
    CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> criaCategoria(@RequestBody  @Valid CadastraCategoriaDto cadastraCategoriaDto){
        return categoriaService.criaCategoria(cadastraCategoriaDto);
    }
    @GetMapping
    public ResponseEntity<CategoriaPageDto> verCategoria(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                         @RequestParam(defaultValue = "10")  @Positive @Max(100) int size){
        return categoriaService.verCategoria(page,size);
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
