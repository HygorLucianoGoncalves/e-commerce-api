package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.AtualizadoUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.CadastraUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.VizualizarUsuarioComListPedidoDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.VizualizarUsuarioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    ResponseEntity<HttpStatus> criandoUsuario(CadastraUsuarioDto dto);

    ResponseEntity<List<VizualizarUsuarioDto>> verUsuario();

    ResponseEntity<List<VizualizarUsuarioComListPedidoDto>> verUsuarioComListaPedidos(String id_usuario);

    ResponseEntity<HttpStatus> atulizarUsuario(String id, AtualizadoUsuarioDto dto);

    ResponseEntity<HttpStatus> deletaUsuario(String id);
}
