package com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Cordenadas;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco")
    private UUID id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String complemento;
    @Embedded
    private Cordenadas cordenadas;
}
