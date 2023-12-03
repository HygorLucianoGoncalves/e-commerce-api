package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.CadastraUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.VizualizarUsuarioDto;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    public ResponseEntity<List<VizualizarUsuarioDto>> verUsuario(){
        return usuarioService.verUsuario();
    };

}
