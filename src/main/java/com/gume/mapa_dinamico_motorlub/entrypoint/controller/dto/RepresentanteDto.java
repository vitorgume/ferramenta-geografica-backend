package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import com.gume.mapa_dinamico_motorlub.domain.Regiao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class RepresentanteDto {

    private Long id;
    private String nome;
    private Regiao regiao;
    private String telefone;
    private String senha;
}
