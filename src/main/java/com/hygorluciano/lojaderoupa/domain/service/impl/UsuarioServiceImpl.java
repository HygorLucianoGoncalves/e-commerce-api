package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.pedidos.VizualizarPedidosDto;
import com.hygorluciano.lojaderoupa.domain.dto.usuario.*;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.repository.PedidoRepository;
import com.hygorluciano.lojaderoupa.domain.repository.UsuarioRepository;
import com.hygorluciano.lojaderoupa.domain.service.UsuarioService;
import com.hygorluciano.lojaderoupa.domain.service.validacao.usuarios.ValidacaoUsuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    final
    UsuarioRepository usuarioRepository;
    final
    PedidoRepository pedidoRepository;
    final
    List<ValidacaoUsuario> validacaoUsuarios;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PedidoRepository pedidoRepository, List<ValidacaoUsuario> validacaoUsuarios) {
        this.usuarioRepository = usuarioRepository;
        this.pedidoRepository = pedidoRepository;
        this.validacaoUsuarios = validacaoUsuarios;
    }

    @Override
    public ResponseEntity<HttpStatus> criandoUsuario(CadastraUsuarioDto dto) {
        for (ValidacaoUsuario validacaoUsuario : validacaoUsuarios) {
            validacaoUsuario.veriificarEmail(dto.email());
        }

        String encryptedSenha = new BCryptPasswordEncoder().encode(dto.senha());

        Usuario novoUsuario = new Usuario(dto.nome(), dto.email(), encryptedSenha);

        usuarioRepository.save(novoUsuario);


        log.info("Usuario cadastrado com sucesso");
        log.info(novoUsuario.getId() + "   Id usuario para teste");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<UsuarioPageDto> verUsuario(@PositiveOrZero int page, @Positive @Max(100) int size) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(PageRequest.of(page, size));

         List<VizualizarUsuarioDto> vizualizarUsuarioDtoList = usuarioPage.get()
                .map(usuario -> new VizualizarUsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getCargo()
                )).collect(Collectors.toList());

        UsuarioPageDto responseDto = new UsuarioPageDto(vizualizarUsuarioDtoList,usuarioPage.getNumber(), usuarioPage.getSize(), usuarioPage.getTotalElements(), usuarioPage.getTotalPages());

        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<List<VizualizarUsuarioComListPedidoDto>> verUsuarioComListaPedidos(String id_usuario) {
        validacaoUsuarios.forEach(v -> v.verificarId(id_usuario));
        Usuario usuario = usuarioRepository.getReferenceById(id_usuario);

        List<VizualizarPedidosDto> listPedidoDtos = usuario.getPedidoList().stream()
                .map(pedido -> new VizualizarPedidosDto(
                        pedido.getId(),
                        pedido.getDataPedido(),
                        pedido.getStatus(),
                        pedido.getTotal(),
                        pedido.getUsuario().getNome()
                )).toList();

        List<VizualizarUsuarioComListPedidoDto> dtos = List.of(
                new VizualizarUsuarioComListPedidoDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        listPedidoDtos
                )
        );

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<HttpStatus> atulizarUsuario(String id, AtualizadoUsuarioDto dto) {
        validacaoUsuarios.forEach(validacaoUsuario -> {
            validacaoUsuario.veriificarEmail(dto.email());
            validacaoUsuario.verificarId(id);
        });

        Usuario usuarioAtualizado = usuarioRepository.getReferenceById(id);

        usuarioAtualizado.setNome(dto.nome() == null ? usuarioAtualizado.getNome() : dto.nome().toUpperCase());
        usuarioAtualizado.setEmail(dto.email() == null ? usuarioAtualizado.getEmail() : dto.email());
        usuarioAtualizado.setSenha(dto.senha() == null ? usuarioAtualizado.getSenha() : dto.senha());

        usuarioRepository.save(usuarioAtualizado);

        log.info(usuarioAtualizado.getEmail() + " Usuario atualizado");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<HttpStatus> deletaUsuario(String id) {
//        validacaoUsuarios.forEach(validacaoUsuario -> validacaoUsuario.verificarId(id));
// METODO ESTA COM ERRO
//        usuarioRepository.deleteById(id);
//
////        log.info(id + "  Usuario deletado com sucesso");
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return null;
    }
}
