package com.hygorluciano.lojaderoupa.domain.repository;

import com.hygorluciano.lojaderoupa.domain.model.Categoria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @AfterEach
    void tearDown() {
        categoriaRepository.deleteAll();
    }

    @Test
    void existsByNomeCategoria() {

        String nomeCategoria = "Roupas";

        // given
        Categoria categoria = new Categoria(
                null,
                nomeCategoria,
                null
        );

        categoriaRepository.save(categoria);

        // when
        boolean expected = categoriaRepository.existsByNomeCategoria(nomeCategoria);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void notExistsByNomeCategoria() {

        String nomeCategoria = "Roupas";
        // given
        Categoria categoria = new Categoria(
                null,
                nomeCategoria,
                null
        );

        categoriaRepository.save(categoria);

        // when
        boolean expected = categoriaRepository.existsByNomeCategoria("roupas");

        // then
        assertThat(expected).isFalse();
    }
}