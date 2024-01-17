package com.hygorluciano.lojaderoupa.domain.dto.usuario;

import java.util.List;

public record UsuarioPageDto(
        List<VizualizarUsuarioDto> usuario,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
