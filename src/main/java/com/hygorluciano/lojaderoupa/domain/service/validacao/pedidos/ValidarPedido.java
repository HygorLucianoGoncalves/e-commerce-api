package com.hygorluciano.lojaderoupa.domain.service.validacao.pedidos;

import com.hygorluciano.lojaderoupa.domain.model.enums.Status;

public interface ValidarPedido {
    void validarid(Long id);
    void validarStatus(Status status);

}
