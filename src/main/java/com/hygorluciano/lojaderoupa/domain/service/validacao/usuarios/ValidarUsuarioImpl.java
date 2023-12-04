package com.hygorluciano.lojaderoupa.domain.service.validacao.usuarios;

import com.hygorluciano.lojaderoupa.domain.exception.ValorExisterExecption;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidarUsuarioImpl implements ValidacaoUsuario{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void veriificarEmail(String email) {
        boolean emailTeste = usuarioRepository.existsByEmail(email);
        if (emailTeste){
            throw new ValorExisterExecption("Email ja esta sendo usado");
        }
    }

    @Override
    public void verificarId(String id) {
        boolean idTeste = usuarioRepository.existsById(id);
        if (!idTeste){
            throw new ValorNaoEncontrado("Id não encotrado ou no existe");
        }
    }
}
