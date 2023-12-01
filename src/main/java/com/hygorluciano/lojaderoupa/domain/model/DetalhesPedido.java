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

    private Integer precoUnitario;

    private Long produto;

    @ManyToOne
    @JoinColumn(name = "pedido")
    private Pedido pedido;
}
