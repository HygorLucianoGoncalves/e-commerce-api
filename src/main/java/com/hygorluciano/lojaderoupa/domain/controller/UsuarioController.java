package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.*;
import com.hygorluciano.lojaderoupa.domain.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<HttpStatus> cadastraUsuario(@RequestBody @Valid CadastraUsuarioDto dados){
        return usuarioService.criandoUsuario(dados);
    }
    @GetMapping
    public ResponseEntity<UsuarioPageDto> verUsuario(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                     @RequestParam(defaultValue = "10") @Positive @Max(100) int size){
        return usuarioService.verUsuario(page, size);
    };

    @GetMapping("/{id}")
    public ResponseEntity<List<VizualizarUsuarioComListPedidoDto>> verUsuarioComListaPedido(@PathVariable String id){
        return usuarioService.verUsuarioComListaPedidos(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> atualizarUsuario(@PathVariable String id, @RequestBody @Valid AtualizadoUsuarioDto dados){
        return usuarioService.atulizarUsuario(id,dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletaUsuario(@PathVariable String id){
        return usuarioService.deletaUsuario(id);
    }
}
