package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import com.hygorluciano.lojaderoupa.domain.model.enums.Cargo;

public record VizualizarUsuarioDto(
        String id,
        String primeiro_nome,
        String sobrenome,
        String email,
        Cargo cargo

) {
}
