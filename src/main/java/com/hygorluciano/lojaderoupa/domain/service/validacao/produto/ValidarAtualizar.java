package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarAtualizar implements ValidacaoProduto {
    @Autowired
    ProdutoRepository produtoRepository;
    @Override
    public void validarNomeProduto(CadastraProdutoDto dto) {

    }

    @Override
    public void validarAtualizar(Long id,AtualizarProdutoDto dto) {
        Optional<Produto> produtoid = produtoRepository.findById(id);
        if (produtoid.isEmpty()){
            throw new ValorNaoEncontrado("Id do produto não existe");
        }
    }
}
