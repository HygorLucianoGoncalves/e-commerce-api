package com.hygorluciano.lojaderoupa.domain.dto.categoria;

import com.hygorluciano.lojaderoupa.domain.model.Categoria;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public record  VerCategoriaDto(

       Long id,
       String nome_categoria
) {
}
