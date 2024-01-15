package com.hygorluciano.lojaderoupa.domain.repository;

import com.hygorluciano.lojaderoupa.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNomeCategoria(String nomeCategoria);

    Categoria getReferenceByNomeCategoria(String nomeCategoria);
}
