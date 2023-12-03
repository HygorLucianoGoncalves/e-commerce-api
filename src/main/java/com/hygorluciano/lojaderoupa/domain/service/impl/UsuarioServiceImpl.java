package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.CadastraUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.VizualizarUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import com.hygorluciano.lojaderoupa.domain.service.UsuarioService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.usuarios.ValidacaoUsuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidacaoUsuario> validacaoUsuarios;

    @Override
    public ResponseEntity<HttpStatus> criandoUsuario(CadastraUsuarioDto dto) {
        validacaoUsuarios.forEach(validacaoUsuario -> {
            validacaoUsuario.veriificarEmail(dto.email());
        });
        Usuario novoUsuario = new Usuario(dto);

        usuarioRepository.save(novoUsuario);
        log.info("Usuario cadastrado com sucesso");
        log.info(novoUsuario.getId() + "   Id usuario para teste");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<VizualizarUsuarioDto>> verUsuario() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream()
                .map(usuario -> new VizualizarUsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                )).toList());
    }

    @Override
    public ResponseEntity<HttpStatus> atulizarUsuario(String id, VizualizarUsuarioDto dto) {

        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deletaUsuario(String id) {
        return null;
    }
}
