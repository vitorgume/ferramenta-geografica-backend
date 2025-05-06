package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "Rota")
@Table(name = "rotas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_rota")
    private UUID id;
    private LocalDate dataCriacao;

    @ManyToMany
    @JoinTable(
            name = "enderecos_rota",
            joinColumns = @JoinColumn(name = "id_rota", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_endereco", nullable = false)
    )
    private List<EnderecoEntity> enderecos;
    private Boolean concluida;
}
