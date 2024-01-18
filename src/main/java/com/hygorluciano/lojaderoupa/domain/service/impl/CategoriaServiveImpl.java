package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.CategoriaPageDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.VerCategoriaComListProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.categoria.VerCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.Categoria;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import com.hygorluciano.lojaderoupa.domain.service.CategoriaService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.categoria.ValidarCategoria;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Transactional
public class CategoriaServiveImpl implements CategoriaService {

    final
    CategoriaRepository categoriaRepository;

    final
    List<ValidarCategoria> validarCategorias;

    @Autowired
    public CategoriaServiveImpl(CategoriaRepository categoriaRepository, List<ValidarCategoria> validarCategorias) {
        this.categoriaRepository = categoriaRepository;
        this.validarCategorias = validarCategorias;
    }

    @Override
    public ResponseEntity<HttpStatus> criaCategoria(CadastraCategoriaDto categoriaDto) {
        validarCategorias.forEach(validarCategoria -> validarCategoria.validarNome(categoriaDto.nome()));

        Categoria novaCategoria = new Categoria(categoriaDto.nome());

        categoriaRepository.save(novaCategoria);

        log.info("Categoria registrada com sucesso " + "NOME CATEGORIA: " + novaCategoria.getNomeCategoria());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    @Override
    public ResponseEntity<CategoriaPageDto> verCategoria(@PositiveOrZero  int page,@Positive @Max(100) int size) {
        Page<Categoria> categoriaPage = categoriaRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"id")));
        List<VerCategoriaDto> categoriaDtos = categoriaPage.get().map(categoria -> new VerCategoriaDto(
                categoria.getId(),
                categoria.getNomeCategoria()
        )).collect(Collectors.toList());
        CategoriaPageDto dto = new CategoriaPageDto(categoriaDtos, categoriaPage.getNumber(), categoriaPage.getSize(), categoriaPage.getTotalElements(), categoriaPage.getTotalPages());

        log.info("Categoria Visualizado com Sucesso");
        return ResponseEntity.ok(dto);



    }

    @Override
    public ResponseEntity<List<VerCategoriaComListProdutoDto>> varCategoriaComListProduto(Long id) {
        validarCategorias.forEach(validarCategoria -> validarCategoria.validarId(id));

        Categoria categoria = categoriaRepository.getReferenceById(id);

        List<VizualizarProdutoDto> listPedido = categoria.getProdutoList().stream()
                .map(produto -> new VizualizarProdutoDto(
                        produto.getId(),
                        produto.getNome(),
                        produto.getImagens(),
                        produto.getCategoria().getNomeCategoria(),
                        produto.getValor(),
                        produto.getEstoque()
                )).toList();

        List<VerCategoriaComListProdutoDto> dtoList = List.of(
                new VerCategoriaComListProdutoDto(
                        categoria.getId(),
                        categoria.getNomeCategoria(),
                        listPedido
                ));
        log.info("Categoria com list de produtos Visualizado com sucesso");
        return ResponseEntity.ok(dtoList);

    }

    @Override
    public ResponseEntity<HttpStatus> atualizarCategoria(Long id, CadastraCategoriaDto cadastraCategoriaDto) {

            validarCategorias.forEach(validarCategoria1 -> {
                validarCategoria1.validarNome(cadastraCategoriaDto.nome());
                validarCategoria1.validarId(id);
            });

            Categoria categoria = categoriaRepository.getReferenceById(id);

            categoria.setNomeCategoria(cadastraCategoriaDto.nome() == null ? categoria.getNomeCategoria() : cadastraCategoriaDto.nome());

            categoriaRepository.save(categoria);

            log.info("Atualizando categoria com ID {}", id);
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
