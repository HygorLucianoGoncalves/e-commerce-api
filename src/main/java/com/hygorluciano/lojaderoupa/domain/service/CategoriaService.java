package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.VerCategoriaComListProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.VerCategoriaDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoriaService {

    ResponseEntity<HttpStatus> criaCategoria(CadastraCategoriaDto categoriaDto);

    ResponseEntity<?> verCatetoria(Pageable pageable);

    ResponseEntity<List<VerCategoriaComListProdutoDto>> varCategoriaComListProduto(Long id);

    ResponseEntity<HttpStatus> atualizarCategoria(Long id,CadastraCategoriaDto cadastraCategoriaDto);

    ResponseEntity<HttpStatus> deletaCategoria(Long id);
}
