package com.hygorluciano.lojaderoupa.domain.model;

import com.hygorluciano.lojaderoupa.domain.dto.produto.CadastraProdutoDto;
import com.hygorluciano.lojaderoupa.domain.model.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produto")
@Table(name = "produto")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String imagens;
    private Double valor;
    private Integer estoque;

    public Produto(CadastraProdutoDto dto) {
        this.nome = dto.nome();
        this.categoria = dto.categoria();
        this.imagens = dto.imagens();
        this.valor = dto.valor();
        this.estoque = dto.estoque();
    }
}
