package com.hygorluciano.lojaderoupa.domain.service.impl;

import com.hygorluciano.lojaderoupa.domain.dto.categoria.CadastraCategoriaDto;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriaServiveImplTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        testRestTemplate.postForEntity("/categoria", new CadastraCategoriaDto("ROUPAS"), Void.class);
    }

    @Test
    void responsePostCategoria() {
        CadastraCategoriaDto cadastraCategoriaDto = new CadastraCategoriaDto("nometeste");
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/categoria", cadastraCategoriaDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void responseGetCategoria() {
        ResponseEntity<String> getResponse = testRestTemplate.getForEntity("/categoria", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void responsePutCategoria(){
        String name = this.categoriaRepository.findById(1L).get().getNomeCategoria();
        assertThat(name).isEqualTo("ROUPAS");


        CadastraCategoriaDto cadastraCategoriaDto = new CadastraCategoriaDto("Novo nome da categoria");
        HttpEntity<CadastraCategoriaDto> request  = new HttpEntity<>(cadastraCategoriaDto);
        ResponseEntity<Void> response = testRestTemplate.exchange("/categoria/1",HttpMethod.PUT,request,Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String afterNamePut = this.categoriaRepository.findById(1L).get().getNomeCategoria();
        assertThat(afterNamePut).isEqualTo("Novo nome da categoria");
    }

    @Test
    void responseDeleteCategoria(){
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange("/categoria/1",HttpMethod.DELETE,null,Void.class);

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


}