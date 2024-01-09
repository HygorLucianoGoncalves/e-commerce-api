package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import com.hygorluciano.lojaderoupa.domain.model.enums.Cargo;

public record VizualizarUsuarioDto(
        String id,
        String nome,
        String email,
        Cargo cargo

) {
}
