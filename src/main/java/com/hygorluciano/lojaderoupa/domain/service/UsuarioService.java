package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    ResponseEntity<HttpStatus> criandoUsuario(CadastraUsuarioDto dto);

    ResponseEntity<UsuarioPageDto> verUsuario(int page, int size);

    ResponseEntity<List<VizualizarUsuarioComListPedidoDto>> verUsuarioComListaPedidos(String id_usuario);

    ResponseEntity<HttpStatus> atulizarUsuario(String id, AtualizadoUsuarioDto dto);

    ResponseEntity<HttpStatus> deletaUsuario(String id);
}
