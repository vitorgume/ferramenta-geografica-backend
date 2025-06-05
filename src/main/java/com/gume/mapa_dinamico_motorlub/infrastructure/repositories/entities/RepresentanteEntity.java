package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import com.gume.mapa_dinamico_motorlub.domain.Regiao;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Representante")
@Table(name = "representantes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RepresentanteEntity {

    @Id
    @Column(name = "id_representante")
    private Long id;
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private Regiao regiao;
    private String cpf;
}
