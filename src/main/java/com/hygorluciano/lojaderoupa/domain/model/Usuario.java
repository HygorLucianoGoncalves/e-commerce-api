package com.hygorluciano.lojaderoupa.domain.model;

import com.hygorluciano.lojaderoupa.domain.dto.usuario.CadastraUsuarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pedido> pedidoList = new ArrayList<>();

    public Usuario(CadastraUsuarioDto dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
    }
}
