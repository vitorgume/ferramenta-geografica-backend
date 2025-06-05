package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import com.gume.mapa_dinamico_motorlub.domain.Segmento;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Empresa")
@Table(name = "empresas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_empresa")
    private UUID id;
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @Enumerated(EnumType.ORDINAL)
    private Segmento segmento;
    private Boolean visitado;
    private String nomeFantasia;
    private String razaoSocial;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_representante", nullable = false)
    private RepresentanteEntity representante;
}
