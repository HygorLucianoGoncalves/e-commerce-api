package com.hygorluciano.lojaderoupa.domain.service.validacao.produto;

import org.springframework.stereotype.Service;


public interface ValidacaoProduto {

    void validarNomeProduto(String nome);

    void validarId(Long id);

    void validarIdCategoria(Long id);

}
