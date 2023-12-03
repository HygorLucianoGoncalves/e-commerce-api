package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.CadastraUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.VizualizarUsuarioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    ResponseEntity<HttpStatus> criandoUsuario(CadastraUsuarioDto dto);

    ResponseEntity<List<VizualizarUsuarioDto>> verUsuario();

    ResponseEntity<HttpStatus> atulizarUsuario(String id,VizualizarUsuarioDto dto);

    ResponseEntity<HttpStatus> deletaUsuario(String id);
}
