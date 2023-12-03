package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.VerCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.model.Categoria;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import com.hygorluciano.lojaderoupa.domain.service.CategoriaService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.categoria.ValidarCategoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CategoriaServiveImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    List<ValidarCategoria> validarCategorias;


    @Override
    public ResponseEntity<HttpStatus> criaCategoria(CadastraCategoriaDto categoriaDto) {
        validarCategorias.forEach(validarCategoria -> validarCategoria.validarNome(categoriaDto.nome()));

        Categoria novaCategoria = new Categoria(null, categoriaDto.nome(), null);

        categoriaRepository.save(novaCategoria);

        log.info("Categoria registrada com sucesso " + novaCategoria.getNomeCategoria());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<VerCategoriaDto>> verCatetoria() {
        return ResponseEntity.ok(categoriaRepository.findAll().stream()
                .map(categoria1 -> new VerCategoriaDto(
                        categoria1.getId(),
                        categoria1.getNomeCategoria()
                )).toList());
    }

    @Override
    public ResponseEntity<HttpStatus> atualizarCategoria(Long id,CadastraCategoriaDto cadastraCategoriaDto) {
         validarCategorias.forEach(validarCategoria1 -> {
             validarCategoria1.validarNome(cadastraCategoriaDto.nome());
             validarCategoria1.validarId(id);
         });

         Categoria categoria = categoriaRepository.getReferenceById(id);

         categoria.setNomeCategoria(cadastraCategoriaDto.nome() == null? categoria.getNomeCategoria() : cadastraCategoriaDto.nome());
         log.info("Atualização na categoria, feita com sucesso");
         categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<HttpStatus> deletaCategoria(Long id) {
      validarCategorias.forEach(validarCategoria1 -> validarCategoria1.validarId(id));

      categoriaRepository.deleteById(id);
      log.info("Categoria deleta com sucesso");
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
