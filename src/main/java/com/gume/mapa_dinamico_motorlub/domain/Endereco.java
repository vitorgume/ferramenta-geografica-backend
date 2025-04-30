package com.gume.mapa_dinamico_motorlub.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String complemento;
    private Cordenadas cordenadas;
}
