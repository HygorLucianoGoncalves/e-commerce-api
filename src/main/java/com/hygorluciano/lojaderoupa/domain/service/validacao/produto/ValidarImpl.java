package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import com.hygorluciano.lojaderoupa.domain.exception.ValorExisterExecption;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidarImpl implements ValidacaoProduto {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public void validarNomeProduto(String nome) {
        if (produtoRepository.existsByNome(nome)) {
            log.error("ERRO : Valor Nome ja existe" + nome);
            throw new ValorExisterExecption("O Produto ja existe");
        }
    }

    @Override
    public void validarId(Long id) {
        boolean idProduto = produtoRepository.existsById(id);

        if (!idProduto) {
            throw new ValorNaoEncontrado("Id Não Existe");
        }
    }

    @Override
    public void validarIdCategoria(Long id) {
        boolean idCategoria = categoriaRepository.existsById(id);
        if (!idCategoria) {
            throw new ValorNaoEncontrado("Id Categoria Não Existe");
        }
    }


}
