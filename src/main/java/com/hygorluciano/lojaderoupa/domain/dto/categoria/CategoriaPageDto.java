package com.hygorluciano.lojaderoupa.domain.dto.categoria;

import java.util.List;

public record CategoriaPageDto (
        List<VerCategoriaDto> categoria,
        int page,
        int size,
        long totalElements,
        int totalPages
){


}
