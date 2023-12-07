package com.hygorluciano.lojaderoupa.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "detalhesPedido")
@Table(name = "detalhesPedido")
public class DetalhesPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private Double precoUnitario;

    @OneToOne
    @JoinColumn(name = "produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido")
    private Pedido pedido;

    public DetalhesPedido(Produto produto, Pedido pedido, Integer quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.precoUnitario = produto.getValor();
        this.quantidade = quantidade;
    }


}
