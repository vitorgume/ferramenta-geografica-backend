package com.gume.mapa_dinamico_motorlub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Empresa {

    private UUID id;
    private String nome;
    private String cnpj;
    private Endereco endereco;
    private Segmento segmento;
    private Boolean visatado;
}
