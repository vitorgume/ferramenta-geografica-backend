package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import com.gume.mapa_dinamico_motorlub.domain.Representante;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Quadro")
@Table(name = "quadros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuadroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_quadro")
    private UUID id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_representante", nullable = false)
    private RepresentanteEntity representante;
}
