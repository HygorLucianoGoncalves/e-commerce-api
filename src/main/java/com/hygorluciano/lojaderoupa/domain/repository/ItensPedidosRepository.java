package com.hygorluciano.lojaderoupa.domain.repository;

import com.hygorluciano.lojaderoupa.domain.model.ItensPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidosRepository extends JpaRepository<ItensPedidos, Long> {
}
