package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.model.Produto;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ValidarProdutoId implements ValidacaoProduto {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void validarNomeProduto(CadastraProdutoDto dto) {

    }

    @Override
    public void validarAtualizar(Long id, AtualizarProdutoDto dto) {

    }

    @Override
    public boolean validarProdutoId(Long id) {
        boolean idProduto = produtoRepository.existsById(id);
        if (!idProduto){
            throw new ValorNaoEncontrado("Id Não Existe");
        }else return true;
    }
}
