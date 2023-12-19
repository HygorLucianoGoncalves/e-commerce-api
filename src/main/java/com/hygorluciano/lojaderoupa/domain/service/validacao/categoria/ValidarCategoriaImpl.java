package com.hygorluciano.lojaderoupa.domain.service.validacao.categoria;

import com.hygorluciano.lojaderoupa.domain.exception.ValorExisterExecption;
import com.hygorluciano.lojaderoupa.domain.exception.ValorNaoEncontrado;
import com.hygorluciano.lojaderoupa.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;

@Component
public class ValidarCategoriaImpl implements ValidarCategoria{

   final
   CategoriaRepository categoriaRepository;

    public ValidarCategoriaImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validarNome(String nome) {
        boolean nomeTeste = categoriaRepository.existsByNomeCategoria(nome);

        if (nomeTeste){
            throw new ValorExisterExecption("Nome da categoria ja existe");
        }
    }

    @Override
    public void validarId(Long id) {
        boolean testeId = categoriaRepository.existsById(id);
        if (!testeId){
            throw new ValorNaoEncontrado("ID CATEORIA NÂO ENCONTRADO ID: { " +id+ " }");
        }

    }
}
