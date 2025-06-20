package com.gume.mapa_dinamico_motorlub.domain.empresa;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class Endereco {

    private UUID id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String complemento;
    private Cordenadas cordenadas;
}
