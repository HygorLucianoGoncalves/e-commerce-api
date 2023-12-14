package com.hygorluciano.lojaderoupa.domain.model;


import com.hygorluciano.lojaderoupa.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pedido")
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.AGUARDANDO_CONFIRMACAO;

    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItensPedidos> itensPedidosList = new ArrayList<>();

    public Pedido(Usuario getUsusario) {
        this.usuario = getUsusario;
    }
}
