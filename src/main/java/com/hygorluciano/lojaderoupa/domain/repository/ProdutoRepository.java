package com.hygorluciano.lojaderoupa.domain.repository;

import com.hygorluciano.lojaderoupa.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    boolean existsByNome(String nome);
}
