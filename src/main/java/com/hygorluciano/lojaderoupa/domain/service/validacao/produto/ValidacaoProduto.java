package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import com.hygorluciano.lojaderoupa.domain.dto.produto.AtualizarProdutoDto;
import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;

public interface ValidacaoProduto {

    void validarNomeProduto(CadastraProdutoDto dto);

    void validarAtualizar(Long id,AtualizarProdutoDto dto);
}
