package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.ProdutoPageDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.model.Categoria;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.service.ProdutoService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.produto.ValidacaoProduto;
import jakarta.transaction.Transactional;
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

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@Slf4j
public class ProdutoServiceImpl implements ProdutoService {
    final
    ProdutoRepository produtoRepository;
    final
    CategoriaRepository categoriaRepository;
    final
    List<ValidacaoProduto> validacaos;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, List<ValidacaoProduto> validacaos) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.validacaos = validacaos;
    }

    @Override
    public ResponseEntity<HttpStatus> cadastraProduto(CadastraProdutoDto dto) {
        validacaos.forEach(validacao -> {
            validacao.validarNomeProduto(dto.nome());
            validacao.validarIdCategoria(dto.nome_categoria().toUpperCase());
        });
        Categoria categoria = categoriaRepository.getReferenceByNomeCategoria(dto.nome_categoria().toUpperCase());
        Produto novoProduto = new Produto(dto, categoria);
        produtoRepository.save(novoProduto);
        log.info(novoProduto.getNome() + " Produto cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ProdutoPageDto> vizualizarProdutoDto(@PositiveOrZero int page,
                                                               @Positive @Max(100) int size) {
        try {
            Page<Produto> produtoPage =  produtoRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.ASC,"id")));

            List<VizualizarProdutoDto> vizualizarProdutoDtos = produtoPage.get()
                    .map(produto -> new VizualizarProdutoDto(
                            produto.getId(),
                            produto.getNome(),
                            produto.getImagens(),
                            produto.getCategoria().getNomeCategoria(),
                            produto.getValor(),
                            produto.getEstoque()))
                    .collect(Collectors.toList());
            log.info("Lista de produtos tudo ok");

            ProdutoPageDto produtoPageDto = new ProdutoPageDto(vizualizarProdutoDtos,produtoPage.getNumber(),produtoPage.getSize(),produtoPage.getTotalElements(),produtoPage.getTotalPages());
            return ResponseEntity.ok(produtoPageDto);
        } catch (Exception e) {
            throw new ValorNaoEncontrado("Erro na metodo GET Produto");
        }

    }

    @Override
    public ResponseEntity<HttpStatus> atualizarProduto(Long id, AtualizarProdutoDto dto) {
        validacaos.forEach(validacaoProduto -> {
            validacaoProduto.validarId(id);
            //validacaoProduto.validarIdCategoria(dto.ca);
        });

        Produto produtoReferenceById = produtoRepository.getReferenceById(id);
        //Categoria categoria = categoriaRepository.getReferenceById(dto.categoria_id());


        produtoReferenceById.setNome(dto.nome() == null ? produtoReferenceById.getNome() : dto.nome());
        //produtoReferenceById.setCategoria(dto.categoria_id() == null ? produtoReferenceById.getCategoria() : categoria);
        produtoReferenceById.setImagens(dto.imagens() == null ? produtoReferenceById.getImagens() : dto.imagens());
        produtoReferenceById.setValor(dto.valor() == null ? produtoReferenceById.getValor() : dto.valor());
        produtoReferenceById.setEstoque(dto.estoque() == null ? produtoReferenceById.getEstoque() : dto.estoque());

        produtoRepository.save(produtoReferenceById);
        log.info("Informações do Produto atualizadas");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<HttpStatus> deletaProduto(Long id) {
        validacaos.forEach(d -> d.validarId(id));

        produtoRepository.deleteById(id);

        log.info(id + " :  Produto deletado com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
