package com.hygorluciano.lojaderoupa.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "itensPedidos")
@Table(name = "itensPedidos")
public class ItensPedidos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private Double precoUnitario;

    private Double subTotal;

    @OneToOne
    @JoinColumn(name = "produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido")
    private Pedido pedido;

    public ItensPedidos(Produto produto, Pedido pedido, Integer quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.precoUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.subTotal = quantidade * produto.getValor();
    }


}
