package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.VizualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import com.hygorluciano.lojaderoupa.domain.service.validacao.produto.ValidacaoProduto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@Slf4j
public class ProdutoServiceImpl implements ProdutoService {
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
        try {
            List<Produto> todosOsProdutos = produtoRepository.findAll();
            List<VizualizarProdutoDto> dados = todosOsProdutos.stream()
                    .map(produto -> new VizualizarProdutoDto(
                            produto.getNome(),
                            produto.getCategoria().getNomeCategoria(),
                            produto.getImagens(),
                            produto.getValor(),
                            produto.getEstoque()))
                    .collect(Collectors.toList());

            log.info("Lista de produtos tudo ok");
            return ResponseEntity.ok(dados);
        } catch (Exception e) {
            throw new ValorNaoEncontrado("Erro na metodo GET");
        }

    }

    @Override
    public ResponseEntity<HttpStatus> atualizarProduto(Long id, AtualizarProdutoDto dto) {

        Produto produtoReferenceById = produtoRepository.getReferenceById(id);

        validacaoProdutos.forEach(v -> v.validarAtualizar(id, dto));

        produtoReferenceById.setNome(dto.nome() == null ? produtoReferenceById.getNome() : dto.nome());
        produtoReferenceById.setCategoria(dto.categoria() == null ? produtoReferenceById.getCategoria() : dto.categoria());
        produtoReferenceById.setImagens(dto.imagens() == null ? produtoReferenceById.getImagens() : dto.imagens());
        produtoReferenceById.setValor(dto.valor() == null ? produtoReferenceById.getValor() : dto.valor());
        produtoReferenceById.setEstoque(dto.estoque() == null ? produtoReferenceById.getEstoque() : dto.estoque());

        produtoRepository.save(produtoReferenceById);
        log.info("Informações do Produto atualizadas");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<HttpStatus> deletaProduto(Long id) {


        return null;
    }
}
