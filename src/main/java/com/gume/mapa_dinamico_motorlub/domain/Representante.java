package com.gume.mapa_dinamico_motorlub.domain;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Regiao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Representante {

    private Long id;
    private String nome;
    private Regiao regiao;
    private String email;
    private String senha;
}
