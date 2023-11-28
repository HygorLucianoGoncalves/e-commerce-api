package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.exception.ValorExisterExecption;
import com.hygorluciano.lojaderoupa.domain.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidarNome implements ValidacaoProduto{
    @Autowired
    ProdutoRepository produtoRepository;
    @Override
    public void validarNomeProduto(CadastraProdutoDto dto) {
        if (produtoRepository.existsByNome(dto.nome())){
            log.error("ERRO : Valor Nome ja existe" + dto.nome());
            throw new ValorExisterExecption("O Produto ja existe");
        }
    }
}
