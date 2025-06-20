package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

    @Entity(name = "Comentario")
@Table(name = "comentarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_comentario")
    private UUID id;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
