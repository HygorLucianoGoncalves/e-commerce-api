package com.hygorluciano.lojaderoupa.domain.service.validacao.pedidos;

import com.hygorluciano.lojaderoupa.domain.exception.ValorExisterExecption;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.model.enums.Status;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidarPedidoImpl implements ValidarPedido{
    @Autowired
    PedidoRepository pedidoRepository;



    @Override
    public void validarid(Long id) {
        boolean validarId = pedidoRepository.existsById(id);
        if (!validarId){
            throw new ValorNaoEncontrado("Id do pedido não encontrado");
        }
    }

    @Override
    public void validarStatus(Status status) {
        if (status == Status.EM_PROCESSAMENTO){
            throw new ValorExisterExecption("O Pedido ja esta em processamento de pagamento ");
        }
    }
}
